package umg.progra2;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import umg.progra2.botTelegram.BotRegistra;
import umg.progra2.botTelegram.botPregunton;
import umg.progra2.model.User;
import umg.progra2.service.UserService;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    // Método para probar la inserción de un usuario
    private static void pruebaInsertaUsuario() {
        // Explicación de las capas y responsabilidades
        /*
            1. Servicio (UserService.java):
               La clase UserService actúa como intermediario entre el controlador y la capa de acceso a datos (DAO).
               Se encarga de la lógica de negocio, validaciones y de coordinar las transacciones.
            2. DAO (UserDao.java):
               Esta clase contiene los métodos para interactuar con la base de datos, usando la
               conexión proporcionada por DatabaseConnection. Aquí es donde se construyen y ejecutan
               las consultas SQL.
            3. Conexión a la Base de Datos (DatabaseConnection.java):
               Esta clase es responsable de proporcionar la conexión a la base de datos. Puede leer la configuración
               desde un archivo de propiedades (application.properties) para obtener los detalles de conexión.
            4. Transacciones (TransactionManager.java):
               Esta clase se encarga de iniciar, confirmar o revertir transacciones en la base de datos.
               Se utiliza para agrupar varias operaciones en una sola transacción y garantizar la integridad de los datos.
            5. Modelo (User.java):
               La clase User representa la estructura de los datos que se insertan en la base de datos.
               Es una clase POJO (Plain Old Java Object) con atributos, getters y setters.
        */

        UserService userService = new UserService();
        User user = new User();
        user.setCarne("0905-12-12345");
        user.setNombre("Andrea Lopez");
        user.setCorreo("ALopez@gmail.com");
        user.setSeccion("A");
        user.setTelegramid(1234567890L);
        user.setActivo("Y");

        try {
            userService.createUser(user);
            System.out.println("Usuario creado exitosamente!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al crear el usuario: " + e.getMessage());
        }
    }


    private static void pruebaActualizacionUsuario() {
        UserService servicioUsuario = new UserService();

        try {
            // Obtener información del usuario por correo electrónico
            User usuarioObtenido = servicioUsuario.getUserByEmail("ALopez@gmail.com");
            if (usuarioObtenido != null) {
                System.out.println("Usuario obtenido: " + usuarioObtenido.getNombre());
                System.out.println("Correo: " + usuarioObtenido.getCorreo());
                System.out.println("ID: " + usuarioObtenido.getId());

                // Actualizar información del usuario
                usuarioObtenido.setCarne("0905-12-12345");
                usuarioObtenido.setNombre("Andrea Ascoli");
                usuarioObtenido.setCorreo("anAscoli@gmail.com");
                usuarioObtenido.setSeccion("A");
                usuarioObtenido.setTelegramid(1234567890L);
                usuarioObtenido.setActivo("Y");

                servicioUsuario.updateUser(usuarioObtenido);
                System.out.println("Usuario actualizado exitosamente!");
            } else {
                System.out.println("No se encontró el usuario con el correo proporcionado.");
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar el usuario: " + e.getMessage());
        }
    }


    private static void pruebaEliminarUsuario() {
        UserService servicioUsuario = new UserService();
        try {
            servicioUsuario.deleteUserByEmail("anAscoli@gmail.com");
            System.out.println("Usuario eliminado exitosamente!");
        } catch (SQLException e) {
            System.out.println("Error al eliminar el usuario: " + e.getMessage());
        }
    }


    public static void explicacionUsoMap() {
        // Creación de un HashMap, que es una implementación común de Map.
        Map<String, String> phoneBook = new HashMap<>();

        // 1. Insertar elementos en el Map usando el método put.
        phoneBook.put("Alice", "123-4567");
        phoneBook.put("Bob", "987-6543");
        phoneBook.put("Charlie", "555-7890");

        // 2. Recuperar un valor a partir de una clave usando el método get.
        String bobPhoneNumber = phoneBook.get("Bob");
        System.out.println("El número de Bob es: " + bobPhoneNumber);

        // 3. Comprobar si una clave existe en el Map.
        if (phoneBook.containsKey("Alice")) {
            System.out.println("El número de Alice es: " + phoneBook.get("Alice"));
        }

        // 4. Recorrer un Map usando un bucle for-each.
        // Se pueden recorrer las claves o los valores.
        System.out.println("\nLista completa de contactos:");
        for (Map.Entry<String, String> entry : phoneBook.entrySet()) {
            System.out.println("Nombre: " + entry.getKey() + ", Número: " + entry.getValue());
        }

        // 5. Eliminar un elemento del Map.
        phoneBook.remove("Charlie");
        System.out.println("\nDespués de eliminar a Charlie, la lista es:");
        for (Map.Entry<String, String> entry : phoneBook.entrySet()) {
            System.out.println("Nombre: " + entry.getKey() + ", Número: " + entry.getValue());
        }

        // 6. Tamaño del Map (número de pares clave-valor).
        System.out.println("\nEl número total de contactos es: " + phoneBook.size());
    }

    private static void iniciarbPregunton() {
        try {

            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);

            botPregunton bot = new botPregunton();

            botsApi.registerBot(bot);

            System.out.println("El Bot está funcionando correctamente.");
        } catch (Exception ex) {

            System.err.println("Error al iniciar el bot: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // Método principal para ejecutar el bot y las pruebas
    public static void main(String[] args) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            BotRegistra bot = new BotRegistra();
            botsApi.registerBot(bot);
            System.out.println("El Bot está funcionando correctamente.");
        } catch (Exception ex) {
            System.out.println("Error al iniciar el bot: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}

