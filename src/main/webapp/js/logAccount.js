/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Eleni Maria
 */

'use strict';

var user=""; 

$( document ).ready(function() {
   var request = new XMLHttpRequest();
    request.onreadystatechange = function () {
        if (request.readyState === 4 && request.status === 200) {
            console.log("Database was created.\n");
        }
        
    };
    request.open("GET", "Initialize_DB", true);
    request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    request.send();
    });  

function submit_company(){
    const account_id= $("#account_id").val();
    const password=$("#password").val(); 
    const name=$("#name").val();
    const expire_date= $("#expire_date").val();
    const account_lim= $("#account_lim").val();
    const current_debt= $("#current_debt").val();
    const available_balance=  $("#available_balance").val();
    const employee_name=  $("#employee_name").val();
    const employee_surname=  $("#employee_surname").val();
    const employee_id=  $("#employee_id").val();
   
    console.log(password);
   
   
    var data = '&account_id='+ account_id+'&name='+name +'&expire_date='+ expire_date
    +'&expire_date='+ expire_date+'&account_lim='+ account_lim+'&current_debt='+ current_debt
    +'&available_balance='+ available_balance+'&password='+ password +
    '&employee_id='+ employee_id +'&employee_name=' + employee_name+'&employee_surname='+ employee_surname;
  
    var AjaxRequest= new XMLHttpRequest();
    AjaxRequest.onreadystatechange=function(){
    if(AjaxRequest.readyState === 4 && AjaxRequest.status === 200){
        var FormData=document.getElementById("signup_response");
        FormData.innerHTML="Sign Up successfully";    
    }
    else if (AjaxRequest.status === 400){   
       Show_HomePage();
      }
    };

    AjaxRequest.open('POST','OpenCompanyAcc',true);
    AjaxRequest.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    AjaxRequest.send(data);
}

function submit_individual(){
    const account_id= $("#account_id").val();
    const password=$("#password").val(); 
    const name=$("#name").val();
    const surname= $("#surname").val();
    const expire_date= $("#expire_date").val();
    const account_lim= $("#account_lim").val();
    const current_debt= $("#current_debt").val();
    const available_balance=  $("#available_balance").val();
   
    console.log(password);
   
   
    var data = '&account_id='+ account_id+'&name='+name+ '&surname='+ surname
    +'&expire_date='+ expire_date+'&expire_date='+ expire_date+'&account_lim='+ account_lim+'&current_debt='+ current_debt
    +'&available_balance='+ available_balance+'&password='+ password;
  
    var AjaxRequest= new XMLHttpRequest();
    AjaxRequest.onreadystatechange=function(){
    if(AjaxRequest.readyState === 4 && AjaxRequest.status === 200){
        var FormData=document.getElementById("signup_response");
        FormData.innerHTML="Sign Up successfully";    
    }
    else if (AjaxRequest.status === 400){   
       
      }
       Show_HomePage();
    };

    AjaxRequest.open('POST','OpenIndividualAcc',true);
    AjaxRequest.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    AjaxRequest.send(data);
}

function submit_company(){
    const account_id= $("#account_id").val();
    const password=$("#password").val(); 
    const name=$("#name").val();
    const surname= $("#surname").val();
    const expire_date= $("#expire_date").val();
    const account_lim= $("#account_lim").val();
    const current_debt= $("#current_debt").val();
    const available_balance=  $("#available_balance").val();
   
    console.log(password);
   
   
    var data = '&account_id='+ account_id+'&name='+name+ '&surname='+ surname
    +'&expire_date='+ expire_date+'&expire_date='+ expire_date+'&account_lim='+ account_lim+'&current_debt='+ current_debt
    +'&available_balance='+ available_balance+'&password='+ password;
  
    var AjaxRequest= new XMLHttpRequest();
    AjaxRequest.onreadystatechange=function(){
    if(AjaxRequest.readyState === 4 && AjaxRequest.status === 200){
        var FormData=document.getElementById("signup_response");
        FormData.innerHTML="Sign Up successfully";    
    }
    else if (AjaxRequest.status === 400){   
       Show_HomePage();
      }
    };

    AjaxRequest.open('POST','OpenSellerAcc',true);
    AjaxRequest.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    AjaxRequest.send(data);
}

function delete_acc(){
    const account_id= $("#account_id").val(); 
   
    var data = '&account_id='+ account_id;
  
    var AjaxRequest= new XMLHttpRequest();
    AjaxRequest.onreadystatechange=function(){
    if(AjaxRequest.readyState === 4 && AjaxRequest.status === 200){
        var FormData=document.getElementById("signup_response");
        FormData.innerHTML="Delete successfully";    
    }
    else if (AjaxRequest.status === 400){   
       Show_HomePage();
      }
    };

    AjaxRequest.open('POST','CloseAcc',true);
    AjaxRequest.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    AjaxRequest.send(data);
}

function Show_HomePage(){
    var table=document.getElementById("table");
    var login_page=document.getElementById("login_page");
    var previous=document.getElementById("previous");
    var column=document.getElementById("column");
    var reg_page=document.getElementById("create");
    var close_acc=document.getElementById("close_acc");
    var purchase_option=document.getElementById("purchase_option");
    var return_option=document.getElementById("return_option");
    var payoff_option=document.getElementById("payoff_option");
    var questions_option=document.getElementById("questions_option");
    var gooduser_option=document.getElementById("gooduser_option");
    var baduser_option=document.getElementById("baduser_option");
    var monthseller_option=document.getElementById("monthseller_option");
    if (previous.checked == false){
      reg_page.style.display = "none";
      purchase_option.style.display = "none";
      return_option.style.display = "none";
      payoff_option.style.display = "none";
      questions_option.style.display = "none";
      gooduser_option.style.display = "none";
      baduser_option.style.display = "none";
      monthseller_option.style.display = "none";
      close_acc.style.display = "none";
      table.style.display = "none";
      login_page.style.display = "block";
      previous.style.display = "block";
      column.style.display = "none";
    } else {
        reg_page.style.display = "none";
        purchase_option.style.display = "none";
        return_option.style.display = "none";
        payoff_option.style.display = "none";
        questions_option.style.display = "none";
        gooduser_option.style.display = "none";
        baduser_option.style.display = "none";
        monthseller_option.style.display = "none";
        close_acc.style.display = "none";
        table.style.display = "block";
        login_page.style.display = "none";
        previous.style.display = "none";
        column.style.display = "block";
    }
}

function Show_LoginPage(){
    var table=document.getElementById("table");
    var login_page=document.getElementById("login_page");
    var prev=document.getElementById("previous");
    var column=document.getElementById("column");
    var b1_check=document.getElementById("b1"); 
    if (b1_check.checked == false){
      table.style.display = "block";
      login_page.style.display = "none";
      prev.style.display = "none";
      column.style.display = "block";
    } else {
        table.style.display = "none";
        login_page.style.display = "block";
        prev.style.display = "block";
        column.style.display = "none";
    }
}

function Show_RegPage(){
    var table=document.getElementById("table");
    var reg_page=document.getElementById("create");
    var prev=document.getElementById("previous");
    var column=document.getElementById("column");
    var b2_check=document.getElementById("b2"); 
    if (b2_check.checked == false){
        table.style.display = "block";
        reg_page.style.display = "none";
        prev.style.display = "none";
        column.style.display = "block";
    } 
    else {
        table.style.display = "none";
        reg_page.style.display = "block";
        prev.style.display = "block";
        column.style.display = "none";
    }
}

function IndividualOption() {
    var individualOpt=document.getElementById("individual");
    var individual=document.getElementById("individual_user");
    var company=document.getElementById("company_user");
    var seller=document.getElementById("seller_user");
    var employee_user=document.getElementById("employee_user");
    
    if (individualOpt.checked == false){
        individual.style.display = "none";
        company.style.display = "none";
        seller.style.display = "none";
        employee_user.style.display = "none";
    } 
    else {
        individual.style.display = "block";
        company.style.display = "none";
        seller.style.display = "none";
        employee_user.style.display = "none";
    }
};

function CompanyOption() {
    var CompanyOpt=document.getElementById("company");
    var company=document.getElementById("company_user");
    var individual=document.getElementById("individual_user");
    var seller=document.getElementById("seller_user");
    
    if (CompanyOpt.checked == false){
        individual.style.display = "none";
        company.style.display = "none";
        seller.style.display = "none";
    } 
    else {
        company.style.display = "block";
        individual.style.display = "none";
        seller.style.display = "none";
    }
};

function EmployeeOption() {
    var EmployeeOpt=document.getElementById("employee");
    var employee_user=document.getElementById("employee_user");
    if (EmployeeOpt.checked == false){
        employee_user.style.display = "none";
    } 
    else {
        employee_user.style.display = "block";
    }
};

function SellerOption() {
    var SellerOpt=document.getElementById("seller");
    var seller=document.getElementById("seller_user");
    var company=document.getElementById("company_user");
    var individual=document.getElementById("individual_user");
    var employee_user=document.getElementById("employee_user");
    
    if (SellerOpt.checked == false){
        individual.style.display = "none";
        company.style.display = "none";
        seller.style.display = "none";
        employee_user.style.display = "none";
    } 
    else {
        seller.style.display = "block";
        company.style.display = "none";
        individual.style.display = "none";
        employee_user.style.display = "none";
    }
};

function Show_CloseAccPage(){
    var table=document.getElementById("table");
    var close_acc=document.getElementById("close_acc");
    var prev=document.getElementById("previous");
    var column=document.getElementById("column");
    var b3_check=document.getElementById("b3"); 
    if (b3_check.checked == false){
        table.style.display = "block";
        close_acc.style.display = "none";
        prev.style.display = "none";
        column.style.display = "block";
    } 
    else {
        table.style.display = "none";
        close_acc.style.display = "block";
        prev.style.display = "block";
        column.style.display = "none";
    }
}

function Show_PurchasePage(){
    var table=document.getElementById("table");
    var purchase_option=document.getElementById("purchase_option");
    var prev=document.getElementById("previous");
    var column=document.getElementById("column");
    var b4_check=document.getElementById("b4"); 
    if (b4_check.checked == false){
        table.style.display = "block";
        purchase_option.style.display = "none";
        prev.style.display = "none";
        column.style.display = "block";
    } 
    else {
        table.style.display = "none";
        purchase_option.style.display = "block";
        prev.style.display = "block";
        column.style.display = "none";
    }
}

function Show_ReturnPage(){
    var table=document.getElementById("table");
    var return_option=document.getElementById("return_option");
    var prev=document.getElementById("previous");
    var column=document.getElementById("column");
    var b5_check=document.getElementById("b5"); 
    if (b5_check.checked == false){
        table.style.display = "block";
        return_option.style.display = "none";
        prev.style.display = "none";
        column.style.display = "block";
    } 
    else {
        table.style.display = "none";
        return_option.style.display = "block";
        prev.style.display = "block";
        column.style.display = "none";
    }
}

function Show_PayOffPage(){
    var table=document.getElementById("table");
    var payoff_option=document.getElementById("payoff_option");
    var prev=document.getElementById("previous");
    var column=document.getElementById("column");
    var b6_check=document.getElementById("b6"); 
    if (b6_check.checked == false){
        table.style.display = "block";
        payoff_option.style.display = "none";
        prev.style.display = "none";
        column.style.display = "block";
    } 
    else {
        table.style.display = "none";
        payoff_option.style.display = "block";
        prev.style.display = "block";
        column.style.display = "none";
    }
}

function Show_QuestionsPage(){
    var table=document.getElementById("table");
    var questions_option=document.getElementById("questions_option");
    var prev=document.getElementById("previous");
    var column=document.getElementById("column");
    var b7_check=document.getElementById("b7"); 
    if (b7_check.checked == false){
        table.style.display = "block";
        questions_option.style.display = "none";
        prev.style.display = "none";
        column.style.display = "block";
    } 
    else {
        table.style.display = "none";
        questions_option.style.display = "block";
        prev.style.display = "block";
        column.style.display = "none";
    }
}

function Show_GoodUserPage(){
    var table=document.getElementById("table");
    var gooduser_option=document.getElementById("gooduser_option");
    var prev=document.getElementById("previous");
    var column=document.getElementById("column");
    var b8_check=document.getElementById("b8"); 
    if (b8_check.checked == false){
        table.style.display = "block";
        gooduser_option.style.display = "none";
        prev.style.display = "none";
        column.style.display = "block";
    } 
    else {
        table.style.display = "none";
        gooduser_option.style.display = "block";
        prev.style.display = "block";
        column.style.display = "none";
    }
}

function Show_BadUserPage(){
    var table=document.getElementById("table");
    var baduser_option=document.getElementById("baduser_option");
    var prev=document.getElementById("previous");
    var column=document.getElementById("column");
    var b9_check=document.getElementById("b9"); 
    if (b9_check.checked == false){
        table.style.display = "block";
        baduser_option.style.display = "none";
        prev.style.display = "none";
        column.style.display = "block";
    } 
    else {
        table.style.display = "none";
        baduser_option.style.display = "block";
        prev.style.display = "block";
        column.style.display = "none";
    }
}

function Show_SellerOfTheMonthPage(){
    var table=document.getElementById("table");
    var monthseller_option=document.getElementById("monthseller_option");
    var prev=document.getElementById("previous");
    var column=document.getElementById("column");
    var b10_check=document.getElementById("b10"); 
    if (b10_check.checked == false){
        table.style.display = "block";
        monthseller_option.style.display = "none";
        prev.style.display = "none";
        column.style.display = "block";
    } 
    else {
        table.style.display = "none";
        monthseller_option.style.display = "block";
        prev.style.display = "block";
        column.style.display = "none";
    }
}