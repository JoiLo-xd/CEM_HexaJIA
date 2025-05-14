package cem.exceptions;

//Excepcion que saldrá en los errores de los participantes(corredores)
public class CorredoresException extends Exception{
    private static String ERROR_CAMPO = "NO SE HA INTRODUCIDO EL TEXTO LOL ";
    public CorredoresException(String message){
        super(message);
    }
    
}
