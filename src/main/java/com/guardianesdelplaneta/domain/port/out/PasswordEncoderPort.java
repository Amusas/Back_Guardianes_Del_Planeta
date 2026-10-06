package com.guardianesdelplaneta.domain.port.out;

/**
 * Puerto de salida para el cifrado y validación de contraseñas.
 */
public interface PasswordEncoderPort {

    /**
     * Codifica una contraseña en texto plano.
     *
     * @param rawPassword Contraseña en texto claro.
     * @return Hash de la contraseña.
     */
    String encode(CharSequence rawPassword);

    /**
     * Verifica si una contraseña en texto claro coincide con un hash almacenado.
     *
     * @param rawPassword     Contraseña en texto claro.
     * @param encodedPassword Hash contra el cual contrastar.
     * @return true si coincide, false si no.
     */
    boolean matches(CharSequence rawPassword, String encodedPassword);
}
