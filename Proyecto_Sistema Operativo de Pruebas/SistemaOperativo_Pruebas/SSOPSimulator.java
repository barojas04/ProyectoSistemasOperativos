public class SSOPSimulator {
   
        public static void main(String[] args) {
            int memoriaTotal = 1024; // Memoria total del sistema en MB
            int impresorasTotales = 3; // Número total de impresoras
            int escaneresTotales = 2; // Número total de escáneres
    
            Planificador planificador = new Planificador(memoriaTotal, impresorasTotales, escaneresTotales);
            CargadorProcesos cargadorProcesos = new CargadorProcesos("procesos.csv");
    
            cargadorProcesos.cargarProcesos(planificador);
            planificador.ejecutarProcesos();
        }
    }
    
