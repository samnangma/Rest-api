package com.demo.dataanalyticrestfulapi.controller;


import com.demo.dataanalyticrestfulapi.service.TransactionService;
import com.github.pagehelper.PageInfo;
import com.demo.dataanalyticrestfulapi.model.Transaction;
import com.demo.dataanalyticrestfulapi.utils.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction")
public class TransactionController {
    private final TransactionService transactionService;
    @GetMapping("/getAllTransaction")
    Response<PageInfo<Transaction>> getAllTransaction(@RequestParam(defaultValue = "1") int page,
                                                      @RequestParam(defaultValue = "5") int size,
                                                      @RequestParam(defaultValue = "0", required = false)  int filter){
        try {
            PageInfo<Transaction> transaction_data = transactionService.getAllTransaction(page, size, filter);
            return Response.<PageInfo<Transaction>>ok().setPayload(transaction_data).setMessage("You got the data Successfully!");
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
            return Response.<PageInfo<Transaction>>exception().setMessage("Error happened! data not found!");
        }
    }

    @PostMapping("/create-new-transaction")
    public Response<Transaction> createTransaction(@RequestBody Transaction transaction) {
        int created = transactionService.createTransaction(transaction);
        if (created > 0) {
            return Response.<Transaction>ok().setPayload(transaction)
                    .setSuccess(true)
                    .setMessage("successfully! added");
        } else {
            return Response.<Transaction>notFound().setPayload(null)
                    .setSuccess(false)
                    .setMessage("Try again! Transaction not found!");
        }
    }
    @DeleteMapping("remove-transaction/{id}")
    public Response<Void> deleteTransaction(@PathVariable int id) {
        try{
            int deleted = transactionService.deleteTransaction(id);
            if (deleted > 0) {
                return Response.<Void>ok().setMessage("Successfully deleted").setSuccess(true);
            } else {
                return Response.<Void>notFound().setMessage("Transaction not found! none data to delete!");
            }
        }catch (Exception e){
            return Response.<Void>exception().setSuccess(true).setMessage("Data not found!");
        }
    }
    @PutMapping ("update/{id}")
    public Response<Void> updateTransaction(@PathVariable int id,
                                            @RequestBody  Transaction transaction  ) {
        try {
            transaction.setId(id);
            int updated = transactionService.updateTransaction(transaction);
            if (updated > 0) {
                return Response.<Void>ok().setSuccess(true).setMessage("SuccessUpdate!");
            } else {
                return Response.<Void>notFound().setSuccess(false).setMessage("Update failed!");
            }
        }catch (Exception e){
            return Response.<Void>exception().setSuccess(false).setMessage("Data not found!");
        }
    }

}
