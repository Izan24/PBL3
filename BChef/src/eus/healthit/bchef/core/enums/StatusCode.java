package eus.healthit.bchef.core.enums;

public enum StatusCode {

	USER_DUPLICATED("El usuario ya existe"), LOGIN_ERROR("El usuario o la contraseña son incorrectos"), BAD_REQUEST(""),
	CONECTION_ERROR("No se pudo establecer conexion"), SERVER_ERROR(""), SUCCESSFUL("");

	private String[] keywords;

	StatusCode(String... keywords) {
		this.keywords = keywords;
	}

	public String[] getKeywords() {
		return keywords;
	}
}
