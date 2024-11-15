class Proceso {
    
        private int id;
        private int prioridad;
        private int tiempoCPURestante;
        private int memoriaRequerida;
        private int impresorasNecesarias;
        private int escaneresNecesarios;
    
        public Proceso(int id, int prioridad, int tiempoCPU, int memoriaRequerida, int impresorasNecesarias, int escaneresNecesarios) {
            this.id = id;
            this.prioridad = prioridad;
            this.tiempoCPURestante = tiempoCPU;
            this.memoriaRequerida = memoriaRequerida;
            this.impresorasNecesarias = impresorasNecesarias;
            this.escaneresNecesarios = escaneresNecesarios;
        }
    
        public int getId() {
            return id;
        }
    
        public int getPrioridad() {
            return prioridad;
        }
    
        public void setPrioridad(int prioridad) {
            this.prioridad = prioridad;
        }
    
        public int getTiempoCPURestante() {
            return tiempoCPURestante;
        }
    
        public void actualizarTiempoCPU(int tiempo) {
            this.tiempoCPURestante -= tiempo;
        }
    
        public int getMemoriaRequerida() {
            return memoriaRequerida;
        }
    
        public int getImpresorasNecesarias() {
            return impresorasNecesarias;
        }
    
        public int getEscaneresNecesarios() {
            return escaneresNecesarios;
        }
    }
    
