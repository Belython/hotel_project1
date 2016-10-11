package by.kanarski.booking.commands.factory;

import by.kanarski.booking.commands.ICommand;
import by.kanarski.booking.commands.impl.admin.GoToAdminPageCommand;
import by.kanarski.booking.commands.impl.admin.databaseCommands.room.AlterRoomsCommand;
import by.kanarski.booking.commands.impl.admin.databaseCommands.room.ConstrainRowCommand;
import by.kanarski.booking.commands.impl.admin.databaseCommands.room.GoToRoomsRedactor;
import by.kanarski.booking.commands.impl.client.*;
import by.kanarski.booking.commands.impl.user.*;

public enum CommandType {
    //user commands
    LOGIN, LOGOUT, REGISTER, GOTOREGISTRATION, GOTOMAIN, GOTOSELECTHOTEL, GOTOSELECTROOMS, MAKEBILL, GOTOACCOUNT, PAYBILL,
    SETLOCALE, CANCELACTION, REFUSEBILL, GOTOREMINDPASSWORD, REMINDPASSWORD, SETCURRENCY,

    //admin commands
    GOTOADMINPAGE,
    GOTOROOMSREDACTOR, ALTERROOMS, CONSTRAINROW;

    public ICommand getCurrentCommand() throws EnumConstantNotPresentException {
        switch (this) {
            case LOGIN:
                return new LoginUserCommand();

            case LOGOUT:
                return new LogoutUserCommand();

            case REGISTER:
                return new RegisterCommand();

            case GOTOREGISTRATION:
                return new GoToRegistrationCommand();

            case GOTOMAIN:
                return new GoToMainPageCommand();

            case SETLOCALE:
                return new SetLocaleCommand();

            case GOTOSELECTHOTEL:
                return new GoToSelectHotelCommand();

            case GOTOSELECTROOMS:
                return new GoToSelectRoomsCommand();

            case MAKEBILL:
                return new MakeBillCommand();

            case GOTOACCOUNT:
                return new GoToAccountCommand();

            case PAYBILL:
                return new PayBillCommand();

            case CANCELACTION:
                return new CancelActionCommand();

            case GOTOADMINPAGE:
                return new GoToAdminPageCommand();

            case GOTOROOMSREDACTOR:
                return new GoToRoomsRedactor();

            case ALTERROOMS:
                return new AlterRoomsCommand();

            case REFUSEBILL:
                return new RefuseBillCommand();

            case GOTOREMINDPASSWORD:
                return new GoToRemindPasswordCommand();

            case REMINDPASSWORD:
                return new RemindPasswordCommand();

            case SETCURRENCY:
                return new SetCurrencyCommand();

            case CONSTRAINROW:
                return new ConstrainRowCommand();


            default:
                throw new EnumConstantNotPresentException(this.getDeclaringClass(), this.name());
        }
    }

}
