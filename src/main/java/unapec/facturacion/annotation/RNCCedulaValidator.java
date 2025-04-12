package unapec.facturacion.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RNCCedulaValidator implements ConstraintValidator<RNCCedulaConstraint, String> {

    @Override
    public void initialize(RNCCedulaConstraint constraint) {

    }

    @Override
    public boolean isValid(String rncField, ConstraintValidatorContext context) {
        return validarRNC(rncField) || validaCedula(rncField);
    }

    static public boolean validarRNC(String rnc){
        final char[] peso = {'7','9','8','6','5','4','3','2'};
        int suma = 0;
        int division = 0;

        if (rnc.length() != 9)
            return false;
        else
        {
            for (int i = 0; i < 8; i++) {
                //para verificar si es un dígito o no
                if (!Character.isDigit(rnc.charAt(i)))
                    return false;

                suma = suma  + (Character.getNumericValue(rnc.charAt(i)) * Character.getNumericValue(peso[i]) );
            }

            division = suma / 11;
            int resto = suma - (division * 11);
            int digito = 0;

            if (resto == 0 )
                digito = 2;
            else if (resto == 1)
                digito = 1;
            else
                digito = 11 - resto;

            if (digito != Character.getNumericValue(rnc.charAt(8)))
                return false;
        }

        return true;
    }

    public static boolean validaCedula(String cedula)
    {
        int suma = 0;
        final char[] peso = { '1', '2', '1', '2', '1', '2', '1', '2', '1', '2' };
        // Comprobaciones iniciales
        if ((cedula == null) || (cedula.length() != 11)){
            return false;
        }
        // Si no es un nº => la descartamos
        try{
            Long.parseLong(cedula);
        }
        catch (Exception e){
            return false;
        }

        for (int i = 0; i < 10; i++){
            int a = Character.getNumericValue(cedula.charAt(i));
            int b = Character.getNumericValue(peso[i]);
            char[] mult = Integer.toString(a * b).toCharArray();
            if (mult.length > 1){
                a = Character.getNumericValue(mult[0]);
                b = Character.getNumericValue(mult[1]);
            }
            else{
                a = 0;
                b = Character.getNumericValue(mult[0]);
            }
            suma = suma + a + b;
        }
        int digito = (10 - (suma % 10)) % 10;
        // Comprobamos que el dígito de control coincide
        if (digito != Character.getNumericValue(cedula.charAt(10))){
            return false;
        }

        return true;
    }
}
