

class Proceso {
    int id;
    int tiempoLlegada;
    int prioridad;
    int tiempoCPU;
    int memoriaRequerida;
    int impresoras;
    int escaneres;
    int camara;
    int parlantes;
    int tiempoRestante;

    Proceso(int id, int tiempoLlegada, int prioridad, int tiempoCPU, int memoriaRequerida,
            int impresoras, int escaneres, int camara, int parlantes) {
        this.id = id;
        this.tiempoLlegada = tiempoLlegada;
        this.prioridad = prioridad;
        this.tiempoCPU = tiempoCPU;
        this.memoriaRequerida = memoriaRequerida;
        this.impresoras = impresoras;
        this.escaneres = escaneres;
        this.camara = camara;
        this.parlantes = parlantes;
        this.tiempoRestante = tiempoCPU;
    }
}
