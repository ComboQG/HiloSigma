public class VistaControl {
    public void mostrarLlegadaVehiculo(String nombre) {
        System.out.println(nombre + " llegó al autoservicio.");
    }

    public void mostrarVehiculoAgregado(String cliente) {
        System.out.println(cliente + " se ha agregado a la cola.");
    }

    public void mostrarAtencionVeiculo(String cliente) {
        System.out.println("Cajero atendiendo a " + cliente);
    }
}
