package br.edu.ifpe.lpoo.project.enums;

public enum Cargo {
	
	BIBLIOTECARIO("Bibliotecário"),
	SUPERVISOR("Supervisor");
	
	private final String CARGO;
	
	private Cargo(String cargo) {
		this.CARGO = cargo;
	}
	
	public String getCargo() {
		return this.CARGO;
	}
	
	public static Cargo cargoFuncionario(String cargoFuncionario) {
		for(Cargo cargo : Cargo.values()) {
			if(cargo.CARGO.equalsIgnoreCase(cargoFuncionario)) {
				return cargo;
			}
		}
		throw new IllegalArgumentException("Tipo inválido");
	}
	
}
