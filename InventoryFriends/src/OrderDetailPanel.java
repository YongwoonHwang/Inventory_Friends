import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class OrderDetailPanel extends JPanel{
    BufferedImage img;
    BufferedImage resizeImg;

    public OrderDetailPanel() {
        Font font = new Font("돋움", Font.PLAIN, 14);
        Font font1 = new Font("돋움", Font.PLAIN, 11);
        setLayout(null);
        try {
            img = ImageIO.read(new File("./img/img_Order.png"));
            resizeImg =  resize(img, 800, 1000);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        JPanel jpImage = new JPanel(new BorderLayout());
        jpImage.setBorder(BorderFactory.createEtchedBorder());
        jpImage.setBounds(144, 210, 80, 70);
        add(jpImage);
        JLabel jlImage = new JLabel("이미지", SwingConstants.CENTER);
        jlImage.setBorder(new LineBorder(Color.red));
        jlImage.setFont(font);
        jpImage.add(jlImage, BorderLayout.CENTER);

        JPanel jpItemName1 = new JPanel(new BorderLayout());
        jpItemName1.setBorder(BorderFactory.createEtchedBorder());
        jpItemName1.setBounds(232, 210, 250, 70);
        add(jpItemName1);
        JLabel jlItemName1 = new JLabel("제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목", SwingConstants.CENTER);
        jlItemName1.setBorder(new LineBorder(Color.red));
        jlItemName1.setFont(font);
        jpItemName1.add(jlItemName1, BorderLayout.CENTER);

        JPanel jpItemQuantity = new JPanel(new BorderLayout());
        jpItemQuantity.setBorder(BorderFactory.createEtchedBorder());
        jpItemQuantity.setBounds(490, 210, 80, 70);
        add(jpItemQuantity);
        JLabel jlItemQuantity = new JLabel("수량", SwingConstants.CENTER);
        jlItemQuantity.setBorder(new LineBorder(Color.red));
        jlItemQuantity.setFont(font);
        jpItemQuantity.add(jlItemQuantity, BorderLayout.CENTER);

        JPanel jpItemPrice1 = new JPanel(new BorderLayout());
        jpItemPrice1.setBorder(BorderFactory.createEtchedBorder());
        jpItemPrice1.setBounds(577, 210, 80, 70);
        add(jpItemPrice1);
        JLabel jlItemPrice1 = new JLabel("가격", SwingConstants.CENTER);
        jlItemPrice1.setBorder(new LineBorder(Color.red));
        jlItemPrice1.setFont(font);
        jpItemPrice1.add(jlItemPrice1, BorderLayout.CENTER);

        JPanel jpMallName = new JPanel(new BorderLayout());
        jpMallName.setBorder(BorderFactory.createEtchedBorder());
        jpMallName.setBounds(230, 368, 80, 35);
        add(jpMallName);
        JLabel jlMallName = new JLabel("쇼핑몰", SwingConstants.CENTER);
        jlMallName.setBorder(new LineBorder(Color.red));
        jlMallName.setFont(font);
        jpMallName.add(jlMallName, BorderLayout.CENTER);

        JPanel jpOrderNum = new JPanel(new BorderLayout());
        jpOrderNum.setBorder(BorderFactory.createEtchedBorder());
        jpOrderNum.setBounds(403, 368, 80, 35);
        add(jpOrderNum);
        JLabel jlOrderNum = new JLabel("주문번호", SwingConstants.CENTER);
        jlOrderNum.setBorder(new LineBorder(Color.red));
        jlOrderNum.setFont(font);
        jpOrderNum.add(jlOrderNum, BorderLayout.CENTER);

        JPanel jpOrderStatus = new JPanel(new BorderLayout());
        jpOrderStatus.setBorder(BorderFactory.createEtchedBorder());
        jpOrderStatus.setBounds(577, 368, 80, 35);
        add(jpOrderStatus);
        JLabel jlOrderStatus = new JLabel("주문 상태", SwingConstants.CENTER);
        jlOrderStatus.setBorder(new LineBorder(Color.red));
        jlOrderStatus.setFont(font);
        jpOrderStatus.add(jlOrderStatus, BorderLayout.CENTER);

        JPanel jpItemName2 = new JPanel(new BorderLayout());
        jpItemName2.setBorder(BorderFactory.createEtchedBorder());
        jpItemName2.setBounds(230, 409, 427, 35);
        add(jpItemName2);
        JLabel jlItemName2 = new JLabel("제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목", SwingConstants.CENTER);
        jlItemName2.setBorder(new LineBorder(Color.red));
        jlItemName2.setFont(font);
        jpItemName2.add(jlItemName2, BorderLayout.CENTER);

        JPanel jpOption = new JPanel(new BorderLayout());
        jpOption.setBorder(BorderFactory.createEtchedBorder());
        jpOption.setBounds(230, 448, 427, 35);
        add(jpOption);
        JLabel jlOption = new JLabel("옵션명", SwingConstants.CENTER);
        jlOption.setBorder(new LineBorder(Color.red));
        jlOption.setFont(font);
        jpOption.add(jlOption, BorderLayout.CENTER);

        JPanel jpItemCode = new JPanel(new BorderLayout());
        jpItemCode.setBorder(BorderFactory.createEtchedBorder());
        jpItemCode.setBounds(230, 488, 80, 35);
        add(jpItemCode);
        JLabel jlItemCode = new JLabel("상품 코드", SwingConstants.CENTER);
        jlItemCode.setBorder(new LineBorder(Color.red));
        jlItemCode.setFont(font);
        jpItemCode.add(jlItemCode, BorderLayout.CENTER);

        JPanel jpItemPrice2 = new JPanel(new BorderLayout());
        jpItemPrice2.setBorder(BorderFactory.createEtchedBorder());
        jpItemPrice2.setBounds(404, 488, 80, 35);
        add(jpItemPrice2);
        JLabel jlItemPrice2 = new JLabel("상품 금액", SwingConstants.CENTER);
        jlItemPrice2.setBorder(new LineBorder(Color.red));
        jlItemPrice2.setFont(font);
        jpItemPrice2.add(jlItemPrice2, BorderLayout.CENTER);

        JPanel jpItemPrice3 = new JPanel(new BorderLayout());
        jpItemPrice3.setBorder(BorderFactory.createEtchedBorder());
        jpItemPrice3.setBounds(577, 488, 80, 35);
        add(jpItemPrice3);
        JLabel jlItemPrice3 = new JLabel("판매 단가", SwingConstants.CENTER);
        jlItemPrice3.setBorder(new LineBorder(Color.red));
        jlItemPrice3.setFont(font);
        jpItemPrice3.add(jlItemPrice3, BorderLayout.CENTER);

        JPanel jpCustomerID = new JPanel(new BorderLayout());
        jpCustomerID.setBorder(BorderFactory.createEtchedBorder());
        jpCustomerID.setBounds(230, 528, 80, 35);
        add(jpCustomerID);
        JLabel jlCustomerID = new JLabel("구매자 ID", SwingConstants.CENTER);
        jlCustomerID.setBorder(new LineBorder(Color.red));
        jlCustomerID.setFont(font);
        jpCustomerID.add(jlCustomerID, BorderLayout.CENTER);

        JPanel jpCustomerName = new JPanel(new BorderLayout());
        jpCustomerName.setBorder(BorderFactory.createEtchedBorder());
        jpCustomerName.setBounds(404, 528, 80, 35);
        add(jpCustomerName);
        JLabel jlCustomerName = new JLabel("구매자이름", SwingConstants.CENTER);
        jlCustomerName.setBorder(new LineBorder(Color.red));
        jlCustomerName.setFont(font);
        jpCustomerName.add(jlCustomerName, BorderLayout.CENTER);

        JPanel jpRecipientName = new JPanel(new BorderLayout());
        jpRecipientName.setBorder(BorderFactory.createEtchedBorder());
        jpRecipientName.setBounds(577, 528, 80, 35);
        add(jpRecipientName);
        JLabel jlRecipientName = new JLabel("수취인이름", SwingConstants.CENTER);
        jlRecipientName.setBorder(new LineBorder(Color.red));
        jlRecipientName.setFont(font);
        jpRecipientName.add(jlRecipientName, BorderLayout.CENTER);

        JPanel jpPhoneNum = new JPanel(new BorderLayout());
        jpPhoneNum.setBorder(BorderFactory.createEtchedBorder());
        jpPhoneNum.setBounds(228, 568, 84, 35);
        add(jpPhoneNum);
        JLabel jlPhoneNum = new JLabel("010-1234-5678", SwingConstants.CENTER);
        jlPhoneNum.setBorder(new LineBorder(Color.red));
        jlPhoneNum.setFont(font1);
        jpPhoneNum.add(jlPhoneNum, BorderLayout.CENTER);

        JPanel jpTelNum = new JPanel(new BorderLayout());
        jpTelNum.setBorder(BorderFactory.createEtchedBorder());
        jpTelNum.setBounds(402, 568, 84, 35);
        add(jpTelNum);
        JLabel jlTelNum = new JLabel("02-123-4567", SwingConstants.CENTER);
        jlTelNum.setBorder(new LineBorder(Color.red));
        jlTelNum.setFont(font1);
        jpTelNum.add(jlTelNum, BorderLayout.CENTER);

//        JPanel jp = new JPanel(new BorderLayout());
//        jp.setBorder(BorderFactory.createEtchedBorder());
//        jp.setBounds(404, 528, 80, 35);
//        add(jp);
//        JLabel jl = new JLabel("수취인이름", SwingConstants.CENTER);
//        jl.setBorder(new LineBorder(Color.red));
//        jl.setFont(font);
//        jp.add(jl, BorderLayout.CENTER);
    }

    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(resizeImg, 0, 0, this); // see javadoc for more info on the parameters
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.pack();
        frame.setSize(800, 1000);
//        frame.setResizable(false);
        frame.add(new OrderDetailPanel());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

}