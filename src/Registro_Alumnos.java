import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Registro_Alumnos {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        List<Alumno> Alumnos = new ArrayList<>();
        int opcion = -1;
        boolean correr = true;

        while (correr) {
            menu();
            try {
                System.out.print("Ingresa la opcion deseada: ");
                opcion = scan.nextInt();
                scan.nextLine();
            } catch (Exception e){
                System.out.println("Debes ingresar un numero entero entre 0 y 6");
                continue;
            }

            switch (opcion){
                case 0:
                    System.out.println("Hasta luego!");
                    correr = false;
                    break;
                case 1:
                    System.out.print("Ingresa el nombre del alumno: ");
                    String nombre = scan.nextLine();
                    System.out.print("Ingresa la nota del alumno: ");
                    String notaString = scan.next();
                    scan.nextLine();
                    Double nota = notaString.contains(",") ?
                            Double.parseDouble(notaString.replace(",", ".")) :
                            Double.parseDouble(notaString);
                    if (nota < 0 || nota > 10) {
                        System.out.println("La nota debe ser un numero entre 0.00 y 10.00");
                        break;
                    }
                    Alumno nuevoAlumno = new Alumno(nombre, nota);
                    Alumnos.add(nuevoAlumno);
                    System.out.println("Alumno registrado");
                    break;
                case 2:
                    String[] nombres = getNombres(Alumnos);
                    Double[] notas = getNotas(Alumnos);
                    System.out.println("Los alumnos registrados son: ");
                    for (int i = 0; i < notas.length; i++) {
                        System.out.printf("El alumno %s con la nota %f%n", nombres[i], notas[i]);
                    }
                    break;
                case 3:
                    notas = getNotas(Alumnos);
                    double promedio = 0;
                    for (int i = 0; i < notas.length; i++) {
                        promedio += notas[i];
                    }
                    promedio /= notas.length;

                    System.out.printf("El promedio de notas es de %f%n", promedio);
                    break;
                case 4:
                    System.out.print("Ingresa el nombre del alumno: ");
                    String nombreBuscado = scan.nextLine();
                    nombres = getNombres(Alumnos);
                    notas = getNotas(Alumnos);
                    int index = List.of(nombres).indexOf(nombreBuscado);
                    if (index == -1) System.out.println("El alumno no existe");
                    else System.out.printf("El alumno %s tiene %f de nota", nombreBuscado, notas[index]);
                case 5:
                    nombres = getNombres(Alumnos);
                    notas = getNotas(Alumnos);
                    System.out.print("Ingresa el nombre del alumno: ");
                    String nombreModificar = scan.nextLine();
                    index = List.of(nombres).indexOf(nombreModificar);
                    if (index == -1) System.out.println("El alumno no existe");
                    else {
                        System.out.print("Ingresa la nueva nota: ");
                        notaString = scan.next();
                        scan.nextLine();
                        Double nuevaNota = notaString.contains(",") ?
                                Double.parseDouble(notaString.replace(",", ".")) :
                                Double.parseDouble(notaString);
                        scan.nextLine();
                        if (nuevaNota < 0 || nuevaNota > 10) {
                            System.out.println("La nota debe ser un numero entre 0.00 y 10.00");
                            break;
                        }
                        Alumno alumnoModificado = Alumnos.get(index);
                        alumnoModificado.nota = nuevaNota;
                        Alumnos.set(index, alumnoModificado);
                    }
                    break;
                case 6:
                    System.out.print("Ingresa el nombre del alumno: ");
                    String nombreEliminar = scan.nextLine();
                    nombres = getNombres(Alumnos);
                    index = List.of(nombres).indexOf(nombreEliminar);
                    if (index == -1) System.out.println("El alumno no existe");
                    else Alumnos.remove(index);
                    System.out.println("Alumno Eliminado");
            }
            System.out.println();
        }


    }

    public static void menu(){
        System.out.println("1.Registrar alumno");
        System.out.println("2.Mostrar todos los alumnos");
        System.out.println("3.Mostrar promedio de todas las notas");
        System.out.println("4.Buscar alumno");
        System.out.println("5.Modificar nota");
        System.out.println("6.Eliminar alumno");
        System.out.println("0.Salir");
    }

    public static String[] getNombres(List<Alumno> listaAlumnos) {
        String[] nombres = new String[listaAlumnos.toArray().length];

        for (int i = 0; i < listaAlumnos.toArray().length; i++) {
            nombres[i] = listaAlumnos.get(i).nombre;
        }

        return nombres;
    }

    public static Double[] getNotas(List<Alumno> listaAlumnos) {
        Double[] notas = new Double[listaAlumnos.toArray().length];

        for (int i = 0; i < listaAlumnos.toArray().length; i++) {
            notas[i] = listaAlumnos.get(i).nota;
        }

        return notas;
    }
}
