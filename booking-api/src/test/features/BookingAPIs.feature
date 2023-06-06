# language: es
Característica: Consumo de APIS de BOOKING
  @GeneraciónToken
 Escenario: Generar token
  Cuando ingreso usuario y clave
  Entonces valido token

  @ConsultaBookingIDs
  Escenario: Obtener Booking id
  Cuando obtengo id
  Entonces valido numero de id

  @ValidandoConectividad
  Escenario: Obtener Ping
  Cuando obtengo Ping
  Entonces valido numero de Ping

  @CasosExito
  Esquema del escenario: Creacion de Booking
    Dado que ingreso una solicitud de booking
    Cuando completo el campo firstname <NOMBRE>
    Y completo el campo Apellido <APELLIDO>
    Y completo el campo Precio <PRECIO>
    Y completo el campo Depósito <DEPOSITOP>
    Y completo el campo Fecha reserva <FECHAINICIO> fecha de pago <FECHAFIN>
    Y completo el campo adicionales <ADICIONALES>
    Y se envia petición
    Entonces verificamos que el registro exista
    Ejemplos:
      | NOMBRE | APELLIDO | PRECIO | DEPOSITOP | FECHAINICIO | FECHAFIN   | ADICIONALES |
      | Jorge  | Baca     | 120    | true      | 2018-01-01  | 2019-01-02 | desayuno    |
      | Arturo | Sancho   | 140    | true      | 2018-01-01  | 2019-01-03 | CENA        |
      | Julio  | Tuco     | 1200   | true      | 2018-01-01  | 2019-01-04 | ALMUERZO    |

  @ActualizacionRegistro
  Escenario: Actualizar registro de Booking
    Dado que tengo un registro de reserva en Booking
    Cuando actualizo el registro
    Entonces verificamos que el registro se haya actualizado


  @EliminarRegistro
  Escenario: Eliminar registro de Booking
    Dado que tengo un registro de reserva en Booking
    Cuando elimino el registro
    Entonces verificamos que el registro no exista


  @ActualizacionRegistroParcial
  Escenario: Actualizar registro parcialmente
    Dado que tengo un registro de reserva en Booking
    Cuando modifico el registro parcialmente
    Entonces verificamos que el registro se haya actualizado
