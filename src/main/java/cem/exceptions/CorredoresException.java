package cem.exceptions;

//Excepcion que saldrá en los errores de los participantes(corredores)
public class CorredoresException extends Exception{
    private static String ERROR_CAMPO = "NO SE HA INTRODUCIDO EL TEXTO LOL ";
    /*
    public static CorredoresException elgException(int num){
        CorredoresException chosen;
        switch (num){
            case 1 -> chosen = new CorredoresException(ERROR_CAMPO);
        }
        if (chosen != null){
            return chosen ;
        }


    }

    */
    public CorredoresException(String message){
        super(message);
    }
    
}
