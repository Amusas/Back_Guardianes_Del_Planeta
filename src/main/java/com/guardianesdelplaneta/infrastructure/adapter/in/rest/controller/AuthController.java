package com.guardianesdelplaneta.infrastructure.adapter.in.rest.controller;

import com.guardianesdelplaneta.domain.model.User;
import com.guardianesdelplaneta.domain.port.in.AuthenticateUserUseCase;
import com.guardianesdelplaneta.domain.port.in.CreateUserUseCase;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.AuthResponseDto;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.UserLoginRequestDto;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.UserRegisterRequestDto;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.UserResponseDto;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.mapper.UserRestMapper;
import com.guardianesdelplaneta.infrastructure.security.jwt.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Adaptador de entrada REST para las operaciones de autenticación y gestión de usuarios.
 * Expone los endpoints POST /auth/v1/user y POST /auth/v1/login.
 */
@RestController
@RequestMapping("/auth/v1")
@Tag(name = "Autenticación", description = "Operaciones relacionadas con la autenticación y la gestión de usuarios")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    private final CreateUserUseCase createUserUseCase;
    private final AuthenticateUserUseCase authenticateUserUseCase;
    private final UserRestMapper userRestMapper;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(
            CreateUserUseCase createUserUseCase,
            AuthenticateUserUseCase authenticateUserUseCase,
            UserRestMapper userRestMapper,
            JwtTokenProvider jwtTokenProvider
    ) {
        this.createUserUseCase = createUserUseCase;
        this.authenticateUserUseCase = authenticateUserUseCase;
        this.userRestMapper = userRestMapper;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    /**
     * Endpoint para registrar un nuevo usuario (Acudiente o Niño).
     *
     * @param request Datos de registro con username, password y rol.
     * @return DTO con la información del usuario creado y código HTTP 201 Created.
     */
    @Operation(summary = "Crear un nuevo usuario", description = "Registra un nuevo usuario (Acudiente o Niño) en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Parámetros de solicitud inválidos", content = @Content),
            @ApiResponse(responseCode = "409", description = "Conflicto, por ejemplo, el nombre de usuario ya existe", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @PostMapping("/user")
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserRegisterRequestDto request) {
        log.info("REST: Solicitud recibida para crear usuario: '{}' con rol: '{}'", request.username(), request.role());

        User userDomain = userRestMapper.toDomain(request);
        User createdUser = createUserUseCase.createUser(userDomain, request.password());
        UserResponseDto response = userRestMapper.toResponseDto(createdUser);

        log.info("REST: Usuario '{}' creado exitosamente con ID: {}", response.username(), response.id());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Endpoint centralizado de autenticación para todos los roles (GUARDIAN y CHILD).
     *
     * @param request Credenciales del usuario (username y password).
     * @return Token JWT con los datos de sesión y código HTTP 200 OK.
     */
    @Operation(summary = "Iniciar sesión", description = "Autentica a un usuario y devuelve un token JWT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autenticado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Parámetros de solicitud inválidos", content = @Content),
            @ApiResponse(responseCode = "401", description = "Credenciales inválidas", content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody UserLoginRequestDto request) {
        log.info("REST: Solicitud de login recibida para el usuario: '{}'", request.username());

        User authenticatedUser = authenticateUserUseCase.authenticate(request.username(), request.password());

        Map<String, Object> extraClaims = Map.of(
                "role", authenticatedUser.getRole().name(),
                "userId", authenticatedUser.getId()
        );

        String token = jwtTokenProvider.generateToken(authenticatedUser.getUsername(), extraClaims);

        AuthResponseDto response = new AuthResponseDto(
                token,
                "Bearer",
                authenticatedUser.getUsername(),
                authenticatedUser.getRole(),
                authenticatedUser.getId(),
                jwtTokenProvider.getExpirationMs()
        );

        log.info("REST: Login exitoso para el usuario '{}'. Token JWT emitido.", authenticatedUser.getUsername());
        return ResponseEntity.ok(response);
    }
}
