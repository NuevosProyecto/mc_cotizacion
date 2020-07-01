package com.proyecto.mc_cotizacion.util;

public class Constants {
    public final static String MAIN_PATH = "/quotes";
    public final static String ID = "/{id}";
    public final static String STATUS = "status/{id}";
    public final static String ERROR = "ERROR";

    public final static String GET_VALUE="Metodo que muestra informacion de cotizaciones por id y por status";
    public final static String GET_NOTE="La cotizaciones se pueden mostrar por id y por status";
    public final static String SAVE_VALUE="Metodo a traves del cual se envia la informacion de la  Cotizacion que  sera registrada dentro de la base de datos";
    public final static String SAVE_NOTE="Para el registro de la cotizacion , sera necesario el llenado de todo los campos , a excepcion de los Id´s los cuales seran generados automaticamente";
    public final static String UPDATE_VALUE="Metodo a traves del cual te permite cambiar datos especificos  de la Cotizacion, permitiendo asi contar con una base de datos actualizada";
    public final static String UPDATE_NOTE="Es necesario el envio del Id de la cotizacion , junto a sus campos debidamente actualizados con la informacion mas reciente";
}
