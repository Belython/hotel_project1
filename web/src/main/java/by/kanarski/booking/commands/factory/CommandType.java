package by.kanarski.booking.commands.factory;

import by.kanarski.booking.commands.ICommand;
import by.kanarski.booking.commands.impl.admin.ApplyChangesCommnad;
import by.kanarski.booking.commands.impl.admin.GetHotelsCommand;
import by.kanarski.booking.commands.impl.admin.GoToAdminPageCommand;
import by.kanarski.booking.commands.impl.user.*;

public enum CommandType {
    //user commands
    LOGIN, LOGOUT, REGISTRATION, GOTOREGISTRATION, GOTOMAIN, SELECTHOTEL, SELECTROOM, MAKEBILL, GOTOACCOUNT, PAYBILL,
    SETLOCALE, CANCELACTION,

    //admin commands
    GOTOADMINPAGE, GETHOTELS, APPLYCHANGES;

    public ICommand getCurrentCommand() throws EnumConstantNotPresentException {
        switch (this) {
            case LOGIN:
                return new LoginUserCommand();

            case LOGOUT:
                return new LogoutUserCommand();

            case REGISTRATION:
                return new RegistrationCommand();

            case GOTOREGISTRATION:
                return new GoToRegistrationCommand();

            case GOTOMAIN:
                return new GoToMainPageCommand();

            case SETLOCALE:
                return new SetLocaleCommand();

            case SELECTHOTEL:
                return new SelectHotelCommand();

            case SELECTROOM:
                return new SelectRoomCommand();

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

            case GETHOTELS:
                return new GetHotelsCommand();

            case APPLYCHANGES:
                return new ApplyChangesCommnad();
            default:
                return new LoginUserCommand();
            //throw new EnumConstantNotPresentException(this.getDeclaringClass(), this.name());
        }
    }

}
