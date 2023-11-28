export interface ValidationError {
    message: string;
    status: string;
    reason: string;
    timestamp: string;
    mapList: null;
    errorList: ErrorItem[]; // Updated to an array of ErrorItem
    fieldErrors: FieldError[];
  }
  
  export interface ErrorItem {
    message: string;
    status: string;
    reason: string;
    timestamp: string;
  }
  
  export interface FieldError {
    field: string;
    message: string;
    reason: string | null;
  }
  