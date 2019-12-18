package com.emito.eribus.model

data class Bus(var busId:String,var busPlateNumber:String,var numberOfSits:Int,var busModel:String){
    constructor():this("","",0,"")
}