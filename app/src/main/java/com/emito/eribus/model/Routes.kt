package com.emito.eribus.model

 class Routes{
    lateinit var  routeId:String
    lateinit var routeCode:String
    lateinit var routefrom:String
    lateinit var routeTo:String
    lateinit var departureLat:String
    lateinit var departureLong:String
    lateinit var destLat:String
    lateinit var destLong:String

      constructor(){

      }
       constructor( routeId:String, routeCode:String, routefrom:String, routeTo:String,
                    departureLat:String, departureLong:String,
                    destLat:String, destLong:String){
           this.routeId=routeId;
        this.routeCode=routeCode;
        this.routefrom=routefrom;
        this.routeTo=routeTo;
        this.departureLat=departureLat;
        this.departureLong=departureLong;
        this.destLat=destLat;
        this.destLong=destLong;
       }
}
