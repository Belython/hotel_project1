package by.kanarski.booking.commands.factory;

import by.kanarski.booking.commands.ICommand;
import by.kanarski.booking.commands.impl.user.*;

public enum CommandType {
    //user commands
    LOGIN, LOGOUT, REGISTRATION, GOTOREGISTRATION, BACK, SELECTHOTEL, SELECTROOM, MAKEBILL, GOTOACCOUNT, PAYBILL,
    SETLOCALE, CALCUL, CANCELACTION;

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

            case BACK:
                return new GoBackCommand();

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

            case CALCUL:
                return new CalculCommand();

            case CANCELACTION:
                return new CancelActionCommand();
            default:
                return new LoginUserCommand();
            //throw new EnumConstantNotPresentException(this.getDeclaringClass(), this.name());
        }
    }

}
