import javax.swing.*;
import java.awt.*;
import java.util.Random;

// Clase Vehículo
class Vehiculo implements Runnable {
    private int id;
    private int tiempoLlegada;
    private VistaPeaje vista;

    public Vehiculo(int id, int tiempoLlegada, VistaPeaje vista) {
        this.id = id;
        this.tiempoLlegada = tiempoLlegada;
        this.vista = vista;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(tiempoLlegada); // Simula el tiempo de llegada
            vista.moverVehiculo(id); // Mueve el vehículo en la interfaz gráfica
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// Clase Caseta
class Caseta {
    private int id;

    public Caseta(int id) {
        this.id = id;
    }

    public int procesarVehiculo() {
        int tiempoPeaje = new Random().nextInt(3000) + 1000; // Tiempo aleatorio de 1-3 segundos
        try {
            Thread.sleep(tiempoPeaje); // Simula el tiempo de procesamiento en el peaje
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return tiempoPeaje;
    }
}

// Clase Peaje
class Peaje {
    private Caseta caseta;

    public Peaje(Caseta caseta) {
        this.caseta = caseta;
    }

    public void procesarVehiculo(Vehiculo vehiculo) {
        int tiempo = caseta.procesarVehiculo();
        System.out.println("Vehículo procesado en " + tiempo + " ms");
    }
}

// Clase VistaPeaje
class VistaPeaje extends JFrame {
    private JPanel panel;
    private JLabel puntoA, puntoB;
    private JLabel[] vehiculos;

    public VistaPeaje(int numVehiculos) {
        setTitle("Simulación de Caseta de Peaje");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(null);

        puntoA = new JLabel("Punto A");
        puntoA.setBounds(50, 50, 100, 30);
        panel.add(puntoA);

        puntoB = new JLabel("Punto B");
        puntoB.setBounds(500, 50, 100, 30);
        panel.add(puntoB);

        vehiculos = new JLabel[numVehiculos];
        for (int i = 0; i < numVehiculos; i++) {
            vehiculos[i] = new JLabel("🚗");
            vehiculos[i].setBounds(50, 100 + (i * 30), 30, 30);
            panel.add(vehiculos[i]);
        }

        add(panel);
        setVisible(true);
    }

    public void moverVehiculo(int id) {
        int x = 50;
        while (x < 500) {
            x += 10;
            vehiculos[id].setBounds(x, 100 + (id * 30), 30, 30);
            try {
                Thread.sleep(50); // Velocidad de movimiento
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Vehículo " + id + " llegó al Punto B.");
    }
}

// Clase LlegadaPeaje
public class LlegadaPeaje {
    public static void main(String[] args) {
        int numVehiculos = 5;
        VistaPeaje vista = new VistaPeaje(numVehiculos);

        for (int i = 0; i < numVehiculos; i++) {
            int tiempoLlegada = new Random().nextInt(5000); // Tiempo de llegada aleatorio (0-5 segundos)
            Vehiculo vehiculo = new Vehiculo(i, tiempoLlegada, vista);
            Thread hiloVehiculo = new Thread(vehiculo);
            hiloVehiculo.start();
        }
    }
}
