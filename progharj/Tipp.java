class Tipp {
  
  String info; // puu tipus hoitav kirje
  int x;       // abiväli
  
  Tipp v, p; // alluvad
  
  Tipp(String info, Tipp v, Tipp p) {
    this.info = info;
    this.v = v;
    this.p = p;
    x = 0;
  }
  
  Tipp(String info) {
    this.info = info;
  }
  
  Tipp() {
    this.info = "";
  }
  
  Tipp(Tipp t) {
    // tipu t kloon, v.a väljad .v ja .p
    this(t.info);
    this.x = t.x;
  }
  
}

