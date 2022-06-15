package loader.modifiers;


import jdk.internal.org.objectweb.asm.Opcodes;
import jdk.internal.org.objectweb.asm.Type;
import jdk.internal.org.objectweb.asm.tree.*;

import java.util.ListIterator;

public abstract class Injector
{
    public abstract void inject();

    public final void setSuperclass(ClassNode node, String name)
    {
        final String old = node.superName;
        node.superName = name;
        for (MethodNode m : node.methods)
        {
            ListIterator<AbstractInsnNode> itr = m.instructions.iterator();
            while (itr.hasNext())
            {
                AbstractInsnNode n = itr.next();
                if (n.getOpcode() == Opcodes.INVOKESPECIAL)
                {
                    MethodInsnNode min = (MethodInsnNode) n;
                    min.owner = min.owner.equals(old) ? name : min.owner;
                }
            }
        }
    }

    public final void injectGetter(ClassNode classNode, String fieldName, String interfaceName, String returnType, boolean isStatic)
    {
        final MethodNode getterMethod = new MethodNode(Opcodes.ACC_PUBLIC, interfaceName, "()" + returnType, null, null);
        getterMethod.visitVarInsn(Opcodes.ALOAD, 0);

        final FieldNode field = getField(classNode, fieldName);
        if (field != null)
        {
            getterMethod.visitFieldInsn(isStatic ? Opcodes.GETSTATIC : Opcodes.GETFIELD, classNode.name, fieldName, field.desc);
            getterMethod.visitInsn(Type.getType(field.desc).getOpcode(Opcodes.IRETURN));
            getterMethod.visitMaxs(0, 0);
            classNode.methods.add(getterMethod);
            System.out.println("Injected " + fieldName + " getter");
        }
    }

    public final void injectGetter(ClassNode from, ClassNode into, String fieldName, String interfaceName, String returnType, boolean isStatic)
    {
        final MethodNode getterMethod = new MethodNode(Opcodes.ACC_PUBLIC, interfaceName, "()" + returnType, null, null);
        getterMethod.visitVarInsn(Opcodes.ALOAD, 0);

        final FieldNode field = getField(from, fieldName);
        if (field != null)
        {
            getterMethod.visitFieldInsn(isStatic ? Opcodes.GETSTATIC : Opcodes.GETFIELD, from.name, fieldName, field.desc);
            getterMethod.visitInsn(Type.getType(field.desc).getOpcode(Opcodes.IRETURN));
            getterMethod.visitMaxs(0, 0);
            into.methods.add(getterMethod);
            System.out.println("Injected " + fieldName + " getter");
        }
    }

    public final FieldNode getField(ClassNode classNode, String name)
    {
        ListIterator<?> li = classNode.fields.listIterator();

        while (li.hasNext())
        {
            FieldNode fn = (FieldNode) li.next();
            if (fn.name.equals(name))
            {
                return fn;
            }
        }
        return null;
    }
}
