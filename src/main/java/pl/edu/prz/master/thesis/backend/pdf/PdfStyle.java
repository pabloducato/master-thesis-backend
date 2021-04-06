package pl.edu.prz.master.thesis.backend.pdf;

import lombok.Data;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.IOException;

@Data
@Component
public class PdfStyle {

    public final static int COLUMN_WIDTH = 45;
    public final static int FONT_SIZE = 8;
    public final static int LEFT_MARGIN = 50;
    public final static PDRectangle PAGE_FORMAT = PDRectangle.A4;
    public final static Color BACKGROUND_COLOR = Color.white;
    public final static Color TEXT_COLOR = Color.black;
    public final static int HEADER_BORDER_WIDTH = 3;
    public final static int NORMAL_BORDER_WIDTH = 1;
    public final static int ROW_HEADER_SIZE = 121;
    public final static int HALF_OF_PAGE_Y = 260;
    private PDFont font;

    public void setFont(Resource font, PDDocument document) throws IOException {
        this.font = PDType0Font.load(document,font.getInputStream());
    }
}
