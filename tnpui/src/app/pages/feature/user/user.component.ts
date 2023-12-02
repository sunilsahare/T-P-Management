import { SelectionModel } from '@angular/cdk/collections';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { User } from 'src/app/model/user-model';
import { RouterService } from 'src/app/service/router.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  pageIndex: number = 0;
  pageSize: number = 10;
  length: number = 0;

  selection = new SelectionModel<any>(true, []);
  pageSixeOptions: number[] = [5, 10, 25, 100];
  constructor(
    private http: HttpClient,
    private routerService: RouterService,
    private _snackBar: MatSnackBar,
    private userService: UserService
  ) {}

  // pageSize: number = 10;
  // pageNo: number = 0;

  displayedColumns: string[] = [
    'select',
    'fullName',
    'email',
    'username',
    'mobile',
    'role',
    'active',
    'action'
  ];
  dataSource = new MatTableDataSource<User>();

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  ngOnInit() {
    this.getAllUsers();
  }

  private getAllUsers() {
    const pageObj = this.getPageObj();
    this.userService.getAllUser(pageObj).subscribe((response: any) => {
      console.log('data - ', response);
      this.dataSource.data = response.content;
      this.pageIndex = response.pageable.pageNumber;
      this.pageSize = response.size;
      this.length = response.totalElements;
    });
  }

  private getPageObj() {
    return {
      page: this.pageIndex,
      size: this.pageSize,
    };
  }

  /**
   * Called when perform next/prev/size change
   * @param event
   */
  public pageEvents(event: PageEvent) {
    this.pageIndex = event?.pageIndex;
    this.pageSize = event?.pageSize;
    this.getAllUsers();
  }

  updateUser() {
    console.log('selection', this.selection);
  }

  isAllSelected() {
    const numSelected = this.selection.selected.length;
    const numRows = this.dataSource.data.length;
    return numSelected == numRows;
  }

  /** Selects all rows if they are not all selected; otherwise clear selection. */
  masterToggle() {
    this.isAllSelected()
      ? this.selection.clear()
      : this.dataSource.data.forEach((row) => this.selection.select(row));
  }

  deleteSelectedUser = () => {
    console.log('this.dataSource --> ', this.dataSource);
    console.log('this.selection --> ', this.selection);
    const selectedUserIdList = this.getSelectedUserIds();
    console.log('selectedRowData', selectedUserIdList);

    this.userService.removeUser(selectedUserIdList).subscribe({
      next: (v) => this.handleDeleteSuccess(v),
      error: (e) => this.handleDeleteError(e),
      complete: () => this.handleDeleteComplete(),
    });
    this.getAllUsers();
  };

  private handleDeleteSuccess(value: any): void {
    this._snackBar.open('Items deleted successfully', 'Success');
    // Additional handling if needed
  }

  private handleDeleteError(error: any): void {
    this._snackBar.open('Error deleting items', error.message);
    // Additional error handling if needed
  }

  private handleDeleteComplete(): void {
    console.info('Delete operation completed');
    this._snackBar.open('Users Successfully Deleted.', 'Success');
    this.selection.clear();
    this.getAllUsers();
  }

  private getSelectedUserIds(): number[] {
    const selectdUsers = this.getSelectedRows();
    return selectdUsers.map((row: { userId: any }) => row.userId);
  }

  private getSelectedRows(): User[] {
    return this.selection.selected;
  }

  /**
   * Update User Accunt Status
   * @param selectedRow - update the status value after api call to the selectdRow
   * @param event - contains status value
   */
  public updateUserStatus(selectedRow:User, event:any) {
    const accountStatus = event.target.checked;
    console.log('accountStatus , ',accountStatus);
    selectedRow.active = accountStatus;
    console.log('selectedRow , ',selectedRow);

  }

}
