package api.script;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention(RetentionPolicy.RUNTIME)

public @interface ScriptManifest
{
    public String name() default "";

    public String[] authors() default "";

    public String description() default "None";

    public double version() default 1.0;
}
