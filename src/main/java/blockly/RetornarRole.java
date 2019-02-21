package blockly;

import cronapi.*;
import cronapi.rest.security.CronappSecurity;
import java.util.concurrent.Callable;


@CronapiMetaData(type = "blockly")
@CronappSecurity(get = "Public", execute = "Public")
public class RetornarRole {

public static final int TIMEOUT = 300;

/**
 *
 * @return Var
 */
// retornarRole
public static Var Executar() throws Exception {
 return new Callable<Var>() {

   private Var grupoLogin = Var.VAR_NULL;

   public Var call() throws Exception {
    grupoLogin = cronapi.list.Operations.getFirst((cronapi.database.Operations.query(Var.valueOf("app.entity.Role"),Var.valueOf("select r.id from Role r where r.user.login = :userLogin"),Var.valueOf("userLogin",cronapi.util.Operations.getCurrentUserName()))));
    System.out.println(grupoLogin.getObjectAsString());
    if (Var.valueOf(grupoLogin.equals(Var.valueOf("Administrators"))).getObjectAsBoolean()) {
        cronapi.util.Operations.callClientFunction(Var.valueOf("cronapi.screen.changeView"), Var.valueOf("#/home/admin/user"), cronapi.list.Operations.newList(Var.valueOf("param0",Var.VAR_NULL)));
    } else {
        cronapi.util.Operations.callClientFunction(Var.valueOf("cronapi.screen.changeView"), Var.valueOf("#/home/error/403"), cronapi.list.Operations.newList(Var.valueOf("param0",Var.VAR_NULL)));
    }
    return Var.VAR_NULL;
   }
 }.call();
}

}

