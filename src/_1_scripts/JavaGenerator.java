package _1_scripts;

class JavaGenerator 
{
	public static void main(String[] args) {
		System.out.println(new JavaGenerator().makeErrorHeader("ForbiddenInvocationError"));
	}
	
	
	String makeErrorHeader(String errorName) {
		return 
				"    private static final long serialVersionUID = 1L;\n" +
				"\n" +
				"    public " + errorName + "() {\n" +
				"        super();\n" +
				"    }\n" +
				"\n" +
				"    public " + errorName + "(String message) {\n" +
				"        super(message);\n" +
				"    }\n" +
				"\n" +
				"    public " + errorName + "(Throwable cause) {\n" +
				"        super(cause);\n" +
				"    }\n" +
				"\n" +
				"    public " + errorName + "(String message, Throwable cause) {\n" +
				"        super(message, cause);\n" +
				"    }" 
		;
	}
}
