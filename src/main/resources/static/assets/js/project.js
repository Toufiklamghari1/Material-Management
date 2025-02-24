var list_besoin;
var besoins_conf={}
var isSent = false;
// delete and add it
$(".custom-checkbox").on('click',function(){

    var row =  $($($(this).parent()).parent());
    var index = parseInt(row.children()[0].textContent)
    if(!row.hasClass("checked-row")) {
        besoins_conf[index+""] = list_besoin[index-1].id;
        row.addClass("checked-row")
    }
    else {
        delete besoins_conf[index];
        row.removeClass("checked-row")
    }
});

function getListBesoin(){
    $.ajax({
        type : "GET",
        url : "getListBesoin",
        success : function(result) {
            list_besoin = result;
        },
        error: function(xhr, status, error){
            console.log("getListBesoin failed")
        }
    })
}

getListBesoin()

function destroyTable() {
    $(".besoin-table tbody").empty();
    $("#confirmer-btn").prop("disabled",true);
}

$(".confirme-btn").on('click',function(){
    if(isSent)
        return
    $.ajax({
        type : "POST",
        url : "listBesoin",
        data:{besoins_conf},
        success : function(result) {
            isSent = true;
            destroyTable();
        },
        error: function(xhr, status, error){
            console.log("send Data failed")
        }
    })
});

// >>>> affectation
var listPers;
var listOrd;
var listImpr;
var isOrdinateure = true;
var ordAffectation={}
var imprAffectation={}
var imprimanteCheck =$(".check-imprimante");
var imprimanteConf = $(".confirmer-imprimante");
var ordinateureConf = $(".confirmer-ordinataure");
var ordinateureCheck =$(".check-ordinateure");
var persCheck =$(".check-enseignant");
function getListAffectation(){
    $.ajax({
        type : "GET",
        url : "getListPersonnel",
        success : function(result) {
            listPers = result;
        },
        error: function(xhr, status, error){
            console.log("get Data failed")
        }
    })
    $.ajax({
        type : "GET",
        url : "getListOrdinateure",
        success : function(result) {
            listOrd = result;
        },
        error: function(xhr, status, error){
            console.log("get Data failed")
        }
    })
    $.ajax({
        type : "GET",
        url : "getListImprimante",
        success : function(result) {
            listImpr = result;
        },
        error: function(xhr, status, error){
            console.log("get Data failed")
        }
    })
}
getListAffectation();
$(".confirmer-ordinataure").on("click",function (){
    isOrdinateure = true;
})
$(".confirmer-imprimante").on("click",function (){
    isOrdinateure = false;
})
$(".check-enseignant").on('click',function(){
    for(var i=0;i<persCheck.length;i++){
        if(persCheck[i].hasAttribute("disabled")){
            persCheck.removeAttr("disabled");
            return
        }
    }
    persCheck.attr("disabled","true");
    $(this).removeAttr("disabled");
    var row =  $($($(this).parent()).parent());
    var index = parseInt(row.children()[0].textContent)
    imprAffectation["pers"] = listPers[index-1].id;
    ordAffectation["pers"] = listPers[index-1].id;

});
$(".check-ordinateure").on('click',function(){
    for(var i=0;i<ordinateureCheck.length;i++){
        if(ordinateureCheck[i].hasAttribute("disabled")){
            ordinateureCheck.removeAttr("disabled");
            ordinateureCheck.attr("disabled","true");
            return
        }
    }
    ordinateureCheck.attr("disabled","true");
    $(this).removeAttr("disabled");
    ordinateureConf.removeAttr('disabled')
    var row =  $($($(this).parent()).parent());
    var index = parseInt(row.children()[0].textContent)
    ordAffectation["ordinateure"] = listOrd[index-1].id;
});
$(".check-impremante").on('click',function(){
    for(var i=0;i<imprimanteCheck.length;i++){
        if(imprimanteCheck[i].hasAttribute("disabled")){
            imprimanteCheck.removeAttr("disabled");
            imprimanteConf.attr('disabled','true')
            return
        }
    }
    imprimanteCheck.attr("disabled","true");
    imprimanteConf.removeAttr('disabled')
    $(this).removeAttr("disabled");
    var row =  $($($(this).parent()).parent());
    var index = parseInt(row.children()[0].textContent)
    imprAffectation["imprimante"] = listImpr[index-1].id;
});
$(".confirme-affectation").on("click",function (){
    var object = imprAffectation;
    if(isOrdinateure)
        object = ordAffectation;
    $.ajax({
        type : "POST",
        url : "storeAffectation",
        data:{object},
        success : function(result) {
        },
        error: function(xhr, status, error){
            console.log("send Data failed")
        }
    })
})