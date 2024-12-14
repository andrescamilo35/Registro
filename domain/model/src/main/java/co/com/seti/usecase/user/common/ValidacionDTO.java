package co.com.seti.usecase.user.common;


public record ValidacionDTO(
        String campo,
        String error
) {
}