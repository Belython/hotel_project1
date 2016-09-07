package by.kanarski.booking.commands.factory;

import by.kanarski.booking.commands.ICommand;
import by.kanarski.booking.commands.impl.admin.databaseCommands.hotels.AlterHotelsCommand;
import by.kanarski.booking.commands.impl.admin.databaseCommands.hotels.GetHotelsCommand;
import by.kanarski.booking.commands.impl.admin.GoToAdminPageCommand;
import by.kanarski.booking.commands.impl.admin.databaseCommands.hotels.SortHotelsTableCommand;
import by.kanarski.booking.commands.impl.user.*;

public enum CommandType {
    //user commands
    LOGIN, LOGOUT, REGISTRATION, GOTOREGISTRATION, GOTOMAIN, SELECTHOTEL, SELECTROOM, MAKEBILL, GOTOACCOUNT, PAYBILL,
    SETLOCALE, CANCELACTION,

    //admin commands
    GOTOADMINPAGE, GETHOTELS, ALTERHOTELS, SORTHOTELSTABLE;

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

            case ALTERHOTELS:
                return new AlterHotelsCommand();
            case SORTHOTELSTABLE:
                return new SortHotelsTableCommand();
            default:
                return new LoginUserCommand();
            //throw new EnumConstantNotPresentException(this.getDeclaringClass(), this.name());
        }
    }

}
