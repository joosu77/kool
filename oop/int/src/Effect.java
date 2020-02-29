public interface Effect {
    // käivitab ründaja, kui vastane saab efektiga pihta
    void onHit(Dude effectTarget);
    // käivitab efektiga pihta saanud vastane iga oma käigu alguses
    void onTurnStart(Dude effectTarget);
    // käivitab efektiga pihta saanud vastane iga oma käigu lõpus
    void onTurnEnd(Dude effectTarget);
    int requiredActionPoints();
    boolean isExpired();
}
