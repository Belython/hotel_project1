package by.kanarski.booking.i18n.l10n.filler;

import java.util.Arrays;
import java.util.List;

public enum FillerManager {
    INDEX (
        Arrays.asList(
            PageTextContentName.INDEX,
            PageTextContentName.HEADER
        )
    ),
    ERROR (
        Arrays.asList(
            PageTextContentName.ERROR,
            PageTextContentName.HEADER
        )
    ),
    REGISTRATION (
        Arrays.asList(
            PageTextContentName.REGISTRATION,
            PageTextContentName.HEADER
        )
    ),
    SELECTHOTEL (
        Arrays.asList(
            PageTextContentName.SELECT_HOTEL,
            PageTextContentName.HEADER
        )
    ),
    SELECTROOM (
        Arrays.asList(
            PageTextContentName.SELECT_ROOM,
            PageTextContentName.HEADER
        )
    ),
    ACCOUNT(
        Arrays.asList(
            PageTextContentName.ACCOUNT,
            PageTextContentName.HEADER
        )
    );

    private List<List<String>> pageDescriptor;

    FillerManager(List<List<String>> pageDescriptor) {
        this.pageDescriptor = pageDescriptor;
    }

    public Filler getFiller() {
        return new Filler(pageDescriptor);
    }

}
