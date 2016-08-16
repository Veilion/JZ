package jei.types;

public interface Accessable 
{
	Visibility getVisibility();
	default boolean isPublic() {
		return this.getVisibility() == Visibility.PUBLIC;
	}
	default boolean isProtected() {
		return this.getVisibility() == Visibility.PROTECTED;
	}
	default boolean isPackagePrivate() {
		return this.getVisibility() == Visibility.PACKAGE_PRIVATE;
	}
	default boolean isPrivate() {
		return this.getVisibility() == Visibility.PRIVATE;
	}
}
