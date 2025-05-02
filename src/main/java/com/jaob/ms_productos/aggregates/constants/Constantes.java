package com.jaob.ms_productos.aggregates.constants;

public class Constantes {
    // Mensajes de éxito
    public static final String MESSAGE_SUCCESSFUL = "Operación realizada con éxito.";
    public static final String MESSAGE_CREATED = "Recurso creado correctamente.";
    public static final String MESSAGE_UPDATED = "Recurso actualizado correctamente.";
    public static final String MESSAGE_FOUND = "Recurso encontrado.";
    public static final String MESSAGE_DELETED = "Recurso eliminado.";

    // Mensajes de error
    public static final String MESSAGE_ERROR = "Ocurrió un error interno en el servidor.";
    public static final String MESSAGE_ERROR_CREATION = "No se pudo crear el recurso.";
    public static final String MESSAGE_ERROR_UPDATE = "No se pudo actualizar el recurso.";
    public static final String MESSAGE_NOT_FOUND = "Recurso no encontrado.";
    public static final String MESSAGE_BAD_REQUEST = "La solicitud contiene datos incorrectos o incompletos.";

    public static final String MESSAGE_REQUIRED_TOKEN = "El token es requerido";
    public static final String MESSAGE_INVALID_TOKEN = "El token JWT ha expirado o es inválido.";

    // Códigos de estado HTTP
    public static final int CODE_SUCCESSFUL = 200;
    public static final int CODE_CREATED = 201;
    public static final int CODE_BAD_REQUEST = 400;
    public static final int CODE_UNAUTHORIZED = 401;
    public static final int CODE_NOT_FOUND = 404;
    public static final int CODE_ERROR = 500;

    // url
    public static final String ENDPOINT_FOR_ALL_ROLES = "/productos/listar";
    public static final String CREATE_ENDPOINT_FOR_ADMIN = "/productos/crear";
    public static final String UPDATE_ENDPOINT_FOR_ADMIN = "/productos/actualizar/**";
    public static final String DELETE_ENDPOINT_FOR_ADMIN = "/productos/eliminar/**";

}
