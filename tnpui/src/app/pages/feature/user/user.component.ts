import { SelectionModel } from '@angular/cdk/collections';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { PopupComponent } from 'src/app/common/popup/popup.component';
import { getDisplayNameByRole } from 'src/app/enum/user-roles';
import { User } from 'src/app/model/user-model';
import { AlertService } from 'src/app/service/alert.service';
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
  @ViewChild('popup') popup!: PopupComponent;

  selection = new SelectionModel<any>(true, []);
  pageSixeOptions: number[] = [5, 10, 25, 100];
  constructor(
    private http: HttpClient,
    private routerService: RouterService,
    private alertService: AlertService,
    private userService: UserService,
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
    const operation = "EDIT";
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

  openPopup() {
    const popupData = {
      title: 'Confirm Deletion',
      message: 'Do you really want to delete the selected Users?',
      type: 'danger',
      btnName: 'Yes'
    };

    this.popup.openPopup(popupData);
  }

  popUpResponse(response: string) {
    if(response === 'OK') {
      this.deleteSelectedUser();
    }
    this.selection.clear();
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
    this.alertService.success('Items deleted successfully');
    // Additional handling if needed
  }

  private handleDeleteError(error: any): void {
    this.selection.clear();
    this.alertService.handleResponse(error.error);
  }

  private handleDeleteComplete(): void {
    console.info('Delete operation completed');
    this.alertService.success('Users Successfully Deleted.');
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
 * Updates the status of a user based on the provided event.
 *
 * This method is typically used in response to a checkbox state change event. It updates
 * the status of the selected user through a call to the UserService
 *
 * @param selectedRow The user whose status is to be updated.
 * @param event The checkbox change event that triggers the status update.
 */
  public updateUserStatus(selectedRow: User, event: any): void {
    const accountStatus: boolean = event.target.checked;
    // If status is null, consider it as inactive
    const currentStatus: boolean = selectedRow.active == null ? false : selectedRow.active;
    this.userService.updateUserStatus(selectedRow.userId, currentStatus, accountStatus)
      .subscribe(
        (response: any) => {
          // Update the user's status in the UI
          selectedRow.active = accountStatus;
          console.log(`User Status Updated: ${selectedRow.username}, Active: ${accountStatus}`);
          this.alertService.success(response.message);
        },
        (error: any) => {
          const errorResponse = error.error;
          event.target.checked = selectedRow.active;
          console.error(`Error updating User Status: ${errorResponse}`);
          this.alertService.handleResponse(errorResponse);
        }
      );
  }
  displayNameByRole(role:string) : string {
    const displayName = getDisplayNameByRole(role);
    return displayName == null ? "" : displayName; 
  }

}
