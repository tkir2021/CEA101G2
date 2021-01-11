"use strict";
class BankAccount {
    constructor(balance = 38500) {
        this.balance = balance;
        $amountTag = $(".amount span");
    }
    deposit(credit) {
        this.balance += credit;
        this.updateView(this.balance);
        return this.balance;
    }
    updateView(newBalance) {
        $amountTag.text(newBalance);
    }
    initializeView() {
        $amountTag.text(this.balance).maskMoney();
    }
}
$amountInput = $(".deposit-amount");
// $amountInput.maskMoney({precision: 0, prefix:"$"});
var account = new BankAccount();
account.initializeView();
function depositCash() {
    var amount = $amountInput.val();
    account.deposit(Number(amount));
    $amountInput.val("");
}
$('.btn').click(function (e) {
    e.preventDefault();
    depositCash();
});