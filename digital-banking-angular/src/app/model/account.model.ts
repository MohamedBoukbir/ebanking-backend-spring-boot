export interface AccountDetails {
  accountId:             string;
  balance:               number;
  accountType:           null;
  currentPage:           number;
  totalPages:            number;
  pageSize:              number;
  accountOperationsDTOS: AccountOperations[];
}

export interface AccountOperations {
  id:            number;
  operationDate: Date;
  amount:        number;
  type:          string;
  description:   string;
}
