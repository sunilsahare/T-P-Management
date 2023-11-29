import { SelectionModel } from '@angular/cdk/collections';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { User } from 'src/app/model/user-model';
import { RouterService } from 'src/app/service/router.service';
import { UserService } from 'src/app/service/user.service';

/**
 * @title Table with pagination
 */
@Component({
  selector: 'app-employer-list',
  templateUrl: './employer-list.component.html',
  styleUrls: ['./employer-list.component.css'],
})
export class EmployerListComponent implements OnInit {

  pageIndex: number = 0;
  pageSize: number = 10;
  length: number = 0;

  selection = new SelectionModel<any>(true, []);
  pageSizeOptions: number[] = [5, 10, 25, 100];
  displayedColumns: string[] = [
    'select',
    'fullName',
    'email',
    'username',
    'mobile',
    'role',
    'active',
  ];
  dataSource = new MatTableDataSource<User>();

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(
    private routerService: RouterService,
    private userService: UserService,
  ) {}

  ngOnInit() {
    this.getAllUsers();
  }

  isAllSelected() {
    const numSelected = this.selection.selected.length;
    const numRows = this.dataSource.data.length;
    return numSelected === numRows;
  }

  /** Selects all rows if they are not all selected; otherwise clear selection. */
  masterToggle() {
    this.isAllSelected()
      ? this.selection.clear()
      : this.dataSource.data.forEach((row) => this.selection.select(row));
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
}
