package dat159.refactoring;

public class EncapsulateAndPullUp {

	int somefield = 0;
}

class Subclass1 extends EncapsulateAndPullUp {
	int somefield = 42;
}
