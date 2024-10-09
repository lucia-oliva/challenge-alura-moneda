import com.alura.conversor.Monedas;
import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public static int mostrarMenu(String monedaSeleccionada) {

    Scanner scanner = new Scanner(System.in);
    int opcion;

    System.out.println("\n\nBienvenido al conversor de monedas");
    System.out.println("-------------------------------------");

    if (monedaSeleccionada != null) {
        System.out.println("1 - Cambiar la moneda a consultar");
        System.out.println("2 - Comparar el valor de la moneda");
    } else {
        System.out.println("1 - Ingresar moneda a consultar");
        System.out.println("\u001B[31m" + "2 - Todavía no se ha ingresado una moneda" + "\u001B[0m");
    }
    System.out.println("3 - Salir");
    opcion = scanner.nextInt();
    return opcion;
}

public static void main() {

    Scanner scanner = new Scanner(System.in);
    int eleccionUsuario;
    String monedaSeleccionada = null;

    do {

        eleccionUsuario = mostrarMenu(monedaSeleccionada);

        if (eleccionUsuario == 2 && monedaSeleccionada == null) {
            System.out.println("Elección no válida");
        } else {
            switch (eleccionUsuario) {
                case 1:
                    System.out.println("Ingresa la moneda a consultar");
                    monedaSeleccionada = scanner.nextLine();
                    break;
                case 2:
                    realizarConversion(monedaSeleccionada);
                    break;
                case 3:
                    System.out.println("Adiós");
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }
    } while (eleccionUsuario != 3);
}

private static void realizarConversion(String monedaBase) {
    double cantidad;
    int opcionMonedaDestino, continuar;
    String monedaDestino;
    String urlApi = "https://v6.exchangerate-api.com/v6/edf34053b6bb467b3ff5c91f/latest/" + monedaBase;

    String respuestaJson = "";
    try {
        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest solicitud = HttpRequest.newBuilder().uri(URI.create(urlApi)).build();

        respuestaJson = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString()).body();

    } catch (Exception e) {
        e.printStackTrace();
    }

    Gson gson = new Gson();
    Monedas datosMonedas = gson.fromJson(respuestaJson, Monedas.class);

    do {
        System.out.println(" Moneda a convertir: " + datosMonedas.getMoneda());
        System.out.println("-------------------------\n");
        System.out.println("Escriba la cantidad a convertir");
        Scanner scanner = new Scanner(System.in);
        cantidad = scanner.nextDouble();
        System.out.println("Escriba la moneda a la que desea convertir");
        System.out.println(datosMonedas.getConversion());
        System.out.println("-------------------------\n");
        opcionMonedaDestino = scanner.nextInt();

        switch (opcionMonedaDestino) {
            case 1:
                monedaDestino = "USD";
                System.out.println("El valor de la moneda en " + monedaDestino + " es: " + datosMonedas.convertir(cantidad, opcionMonedaDestino));
                break;
            case 2:
                monedaDestino = "ARS";
                System.out.println("El valor de la moneda en " + monedaDestino + " es: " + datosMonedas.convertir(cantidad, opcionMonedaDestino));
                break;
            case 3:
                monedaDestino = "BRL";
                System.out.println("El valor de la moneda en " + monedaDestino + " es: " + datosMonedas.convertir(cantidad, opcionMonedaDestino));
                break;
            case 4:
                monedaDestino = "COP";
                System.out.println("El valor de la moneda en " + monedaDestino + " es: " + datosMonedas.convertir(cantidad, opcionMonedaDestino));
                break;
            case 5:
                monedaDestino = "CLP";
                System.out.println("El valor de la moneda en " + monedaDestino + " es: " + datosMonedas.convertir(cantidad, opcionMonedaDestino));
                break;
            default:
                System.out.println("Opción no válida");
        }
        System.out.println("¿Desea realizar otra conversión? (1 = sí / 2 = no)");
        continuar = scanner.nextInt();
    } while (continuar == 1);
}
