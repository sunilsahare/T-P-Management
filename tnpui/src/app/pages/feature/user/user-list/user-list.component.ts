import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import {
  AgGridEvent,
  CheckboxSelectionCallbackParams,
  ColDef,
  GridOptions,
  HeaderCheckboxSelectionCallbackParams,
  IServerSideDatasource,
} from 'ag-grid-community';
import { RouterService } from 'src/app/service/router.service';
import { UserService } from 'src/app/service/user.service';
import { User } from 'src/app/model/user-model';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css'],
})
export class UserListComponent implements OnInit {
  private gridColumnAPi!: any;

  defaultPageSize = 15;
  currentPage = 0;
  paginationPageSize = 5; // Number of rows to display per page
  public cacheBlockSize = 10;
  gridApi: any;

  constructor(
    private http: HttpClient,
    private routerService: RouterService,
    private userService: UserService
  ) {}

  ngOnInit(): void {
    // this.getAllUsers(this.paginationPageSize);
    this.dataSource = this.getServerSideDatasource();
  }

  ngAfterViewInit() {}

  // public gridApi: any;

  gridOptions: GridOptions = {
    defaultColDef: {
      sortable: true,
      resizable: true,
      filter: true,
      flex: 1,
    },
    rowModelType: 'infinite',
  };

  dataSource: any = this.getServerSideDatasource();

  public columnDefs: ColDef[] = [
    {
      // field: '',
      checkboxSelection: checkboxSelection,
      // headerCheckboxSelection: headerCheckboxSelection,
    },
    { field: 'fullName' },
    { field: 'email' },
    { field: 'username' },
    { field: 'password' },
    { field: 'mobile' },
    { field: 'role' },
    { field: 'active' },
  ];

  // public defaultColDef: ColDef = {
  //   sortable: true,
  //   resizable: true,
  //   filter: true,
  //   flex: 1,
  // };

  public rowSelection: 'single' | 'multiple' = 'multiple';
  public pivotPanelShow: 'always' | 'onlyWhenPivoting' | 'never' = 'always';
  public rowData!: User[];

  onGridReady(params: any) {
    console.log('grid ready ', params);
    this.gridApi = params.api;
    this.gridColumnAPi = params.columnApi;
    // this.getAllUsers(this.paginationPageSize);
  }

  getServerSideDatasource(): IServerSideDatasource {
    return {
      getRows: (params: any) => {
        let sort = undefined;
        let colId = undefined;

        if (params.sortModel[0]) {
          sort = params.sortModel[0].sort;
          colId = params.sortModel[0].colId;
        }

        let request = {
          colId: colId,
          sort: sort,
          filterModel: params.filterModel,
          data: undefined,
        };

        console.log('[Datasource] - rows requested by grid: ', params.request);
        const currentPage = this.gridApi.paginationGetCurrentPage();
        const pageSize = this.gridApi.paginationGetPageSize();
        this.userService
          .getAllUser(currentPage, pageSize)
          .subscribe((response: any) => {
            console.log('data - ', response);
            // this.rowData = data.content;
            // this.paginationPageSize = data.totalElements;
            setTimeout(function () {
            if (response.success) {
              // call the success callback
              params.success({
                rowData: response.content,
                rowCount: response.totalElements,
              });
            } else {
              console.log('error: ');
              params.fail();
            }
          }, 200);
          });

      },
    };
  }

  private getAllUsers(pageSize: number) {
    const currentPage = this.gridApi.paginationGetCurrentPage() + 1; // Ag-Grid page index is 0-based
    this.userService
      .getAllUser(currentPage, pageSize)
      .subscribe((data: any) => {
        console.log('data - ', data);
        this.rowData = data.content;
        this.paginationPageSize = data.totalElements;
      });
  }
  // private getAllUsers(pageSize: number) {
  //   const currentPage = this.gridApi.paginationGetCurrentPage() + 1; // Ag-Grid page index is 0-based
  //   this.userService
  //     .getAllUser(currentPage, pageSize)
  //     .subscribe((data: any) => {
  //       console.log('data - ',data);
  //       this.rowData = data.content;
  //       this.paginationPageSize=data.totalElements;
  //     });
  // }

  // navigate to add/edit student page
  public navigateToAddEditPage() {
    this.routerService.navigateToUrl('tnp', 'user/student');
  }

  selectedRowCount: number = 0;

  onSelectionChanged(event: AgGridEvent): void {
    this.selectedRowCount = event.api.getSelectedNodes().length;
    console.log('selectedRowCount', this.selectedRowCount);
  }

  onPaginationChanged(): void {
    // this.getAllUsers(this.paginationPageSize);
  }

  deleteSelectedUser = () => {
    const selectedUserIdList = this.getSelectedUserIds(this.getSelectedRows());
    console.log('selectedRowData', selectedUserIdList);

    this.userService.removeUser(selectedUserIdList).subscribe({
      next: (v) => this.handleDeleteSuccess(v),
      error: (e) => this.handleDeleteError(e),
      complete: () => this.handleDeleteComplete(),
    });
    this.getAllUsers(this.paginationPageSize);
  };

  private handleDeleteSuccess(value: any): void {
    console.log('Items deleted successfully', value);
    // Additional handling if needed
  }

  private handleDeleteError(error: any): void {
    console.error('Error deleting items', error);
    // Additional error handling if needed
  }

  private handleDeleteComplete(): void {
    console.info('Delete operation completed');
    // Additional completion handling if needed
    this.getAllUsers(this.paginationPageSize);
  }

  private getSelectedRows(): any {
    const selectedNodes = this.gridApi.getSelectedNodes();
    return selectedNodes.map((node: { data: any }) => node.data);
  }

  private getSelectedUserIds(selectedRowData: any): number[] {
    return selectedRowData.map((row: { userId: any }) => row.userId);
  }
}

var checkboxSelection = function (params: CheckboxSelectionCallbackParams) {
  // we put checkbox on the name if we are not doing grouping
  return params.columnApi.getRowGroupColumns().length === 0;
};
var headerCheckboxSelection = function (
  params: HeaderCheckboxSelectionCallbackParams
) {
  // we put checkbox on the name if we are not doing grouping
  return params.columnApi.getRowGroupColumns().length === 0;
};
