package main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class OrderDetailPanel extends JPanel{
    BufferedImage img;
    BufferedImage resizeImg;
    JPanel jpImage, jpItemName1, jpItemQuantity, jpItemPrice1, jpMallName, jpOrderNum, jpOrderStatus,
            jpItemName2, jpOption, jpItemCode, jpItemPrice2, jpItemPrice3, jpCustomerID, jpCustomerName,
            jpRecipientName, jpPhoneNum, jpTelNum, jpEmail, jpOrderDate, jpCollectDate, jpShippingDate,
            jpTrackingNumber, jpShippingMethod, jpCourier, jpAddress, jpShippingMessage;
    JLabel jlImage, jlItemName1, jlItemQuantity, jlItemPrice1, jlMallName, jlOrderNum, jlOrderStatus,
            jlItemName2, jlOption, jlItemCode, jlItemPrice2, jlItemPrice3, jlCustomerID, jlCustomerName,
            jlRecipientName, jlPhoneNum, jlTelNum, jlEmail, jlOrderDate, jlCollectDate, jlShippingDate,
            jlTrackingNumber, jlShippingMethod, jlCourier, jlAddress, jlShippingMessage;


    public OrderDetailPanel() {
        Font font = new Font("돋움", Font.PLAIN, 12);
        Font font1 = new Font("돋움", Font.PLAIN, 11);
        setLayout(null);
        setPreferredSize(new Dimension(800, 1000));
        try {
            img = ImageIO.read(new File("./img/img_Order.png"));
            resizeImg =  resize(img, 800, 1000);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        jpImage = new JPanel(new BorderLayout());
        jpImage.setBackground(Color.WHITE);
//        jpImage.setBorder(BorderFactory.createEtchedBorder());
        add(jpImage);
        jlImage = new JLabel("이미지", SwingConstants.CENTER);
//        jlImage.setBorder(new LineBorder(Color.red));
        jlImage.setFont(font);
        jpImage.add(jlImage, BorderLayout.CENTER);

        jpItemName1 = new JPanel(new BorderLayout());
        jpItemName1.setBackground(Color.WHITE);
        add(jpItemName1);
        jlItemName1 = new JLabel("제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목", SwingConstants.CENTER);
        jlItemName1.setFont(font);
        jpItemName1.add(jlItemName1, BorderLayout.CENTER);

        jpItemQuantity = new JPanel(new BorderLayout());
        jpItemQuantity.setBackground(Color.WHITE);
        add(jpItemQuantity);
        jlItemQuantity = new JLabel("수량", SwingConstants.CENTER);
        jlItemQuantity.setFont(font);
        jpItemQuantity.add(jlItemQuantity, BorderLayout.CENTER);

        jpItemPrice1 = new JPanel(new BorderLayout());
        jpItemPrice1.setBackground(Color.WHITE);
        add(jpItemPrice1);
        jlItemPrice1 = new JLabel("가격", SwingConstants.CENTER);
        jlItemPrice1.setFont(font);
        jpItemPrice1.add(jlItemPrice1, BorderLayout.CENTER);

        jpMallName = new JPanel(new BorderLayout());
        jpMallName.setBackground(Color.WHITE);
        add(jpMallName);
        jlMallName = new JLabel("쇼핑몰", SwingConstants.CENTER);
        jlMallName.setFont(font);
        jpMallName.add(jlMallName, BorderLayout.CENTER);

        jpOrderNum = new JPanel(new BorderLayout());
        jpOrderNum.setBackground(Color.WHITE);
        add(jpOrderNum);
        jlOrderNum = new JLabel("주문번호", SwingConstants.CENTER);
        jlOrderNum.setFont(font);
        jpOrderNum.add(jlOrderNum, BorderLayout.CENTER);

        jpOrderStatus = new JPanel(new BorderLayout());
        jpOrderStatus.setBackground(Color.WHITE);
        add(jpOrderStatus);
        jlOrderStatus = new JLabel("주문 상태", SwingConstants.CENTER);
        jlOrderStatus.setFont(font);
        jpOrderStatus.add(jlOrderStatus, BorderLayout.CENTER);

        jpItemName2 = new JPanel(new BorderLayout());
        jpItemName2.setBackground(Color.WHITE);
        add(jpItemName2);
        jlItemName2 = new JLabel("제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목", SwingConstants.CENTER);
        jlItemName2.setFont(font);
        jpItemName2.add(jlItemName2, BorderLayout.CENTER);

        jpOption = new JPanel(new BorderLayout());
        jpOption.setBackground(Color.WHITE);
        add(jpOption);
        jlOption = new JLabel("옵션명", SwingConstants.CENTER);
        jlOption.setFont(font);
        jpOption.add(jlOption, BorderLayout.CENTER);

        jpItemCode = new JPanel(new BorderLayout());
        jpItemCode.setBackground(Color.WHITE);
        add(jpItemCode);
        jlItemCode = new JLabel("상품 코드", SwingConstants.CENTER);
        jlItemCode.setFont(font);
        jpItemCode.add(jlItemCode, BorderLayout.CENTER);

        jpItemPrice2 = new JPanel(new BorderLayout());
        jpItemPrice2.setBackground(Color.WHITE);
        add(jpItemPrice2);
        jlItemPrice2 = new JLabel("상품 금액", SwingConstants.CENTER);
        jlItemPrice2.setFont(font);
        jpItemPrice2.add(jlItemPrice2, BorderLayout.CENTER);

        jpItemPrice3 = new JPanel(new BorderLayout());
        jpItemPrice3.setBackground(Color.WHITE);
        add(jpItemPrice3);
        jlItemPrice3 = new JLabel("판매 단가", SwingConstants.CENTER);
        jlItemPrice3.setFont(font);
        jpItemPrice3.add(jlItemPrice3, BorderLayout.CENTER);

        jpCustomerID = new JPanel(new BorderLayout());
        jpCustomerID.setBackground(Color.WHITE);
        add(jpCustomerID);
        jlCustomerID = new JLabel("구매자 ID", SwingConstants.CENTER);
        jlCustomerID.setFont(font);
        jpCustomerID.add(jlCustomerID, BorderLayout.CENTER);

        jpCustomerName = new JPanel(new BorderLayout());
        jpCustomerName.setBackground(Color.WHITE);
        add(jpCustomerName);
        jlCustomerName = new JLabel("구매자이름", SwingConstants.CENTER);
        jlCustomerName.setFont(font);
        jpCustomerName.add(jlCustomerName, BorderLayout.CENTER);

        jpRecipientName = new JPanel(new BorderLayout());
        jpRecipientName.setBackground(Color.WHITE);
        add(jpRecipientName);
        jlRecipientName = new JLabel("수취인이름", SwingConstants.CENTER);
        jlRecipientName.setFont(font);
        jpRecipientName.add(jlRecipientName, BorderLayout.CENTER);

        jpPhoneNum = new JPanel(new BorderLayout());
        jpPhoneNum.setBackground(Color.WHITE);
        add(jpPhoneNum);
        jlPhoneNum = new JLabel("010-1234-5678", SwingConstants.CENTER);
        jlPhoneNum.setFont(font1);
        jpPhoneNum.add(jlPhoneNum, BorderLayout.CENTER);

        jpTelNum = new JPanel(new BorderLayout());
        jpTelNum.setBackground(Color.WHITE);
        add(jpTelNum);
        jlTelNum = new JLabel("02-123-4567", SwingConstants.CENTER);
        jlTelNum.setFont(font1);
        jpTelNum.add(jlTelNum, BorderLayout.CENTER);

        jpEmail = new JPanel(new BorderLayout());
        jpEmail.setBackground(Color.WHITE);
        add(jpEmail);
        jlEmail = new JLabel("<html>ggoma6245<br>@naver.com</html>", SwingConstants.CENTER);
        jlEmail.setFont(font1);
        jpEmail.add(jlEmail, BorderLayout.CENTER);

        jpOrderDate = new JPanel(new BorderLayout());
        jpOrderDate.setBackground(Color.WHITE);
        add(jpOrderDate);
        jlOrderDate = new JLabel("220202", SwingConstants.CENTER);
        jlOrderDate.setFont(font);
        jpOrderDate.add(jlOrderDate, BorderLayout.CENTER);

        jpCollectDate = new JPanel(new BorderLayout());
        jpCollectDate.setBackground(Color.WHITE);
        add(jpCollectDate);
        jlCollectDate = new JLabel("22023", SwingConstants.CENTER);
        jlCollectDate.setFont(font);
        jpCollectDate.add(jlCollectDate, BorderLayout.CENTER);

        jpShippingDate = new JPanel(new BorderLayout());
        jpShippingDate.setBackground(Color.WHITE);
        add(jpShippingDate);
        jlShippingDate = new JLabel("220208", SwingConstants.CENTER);
        jlShippingDate.setFont(font);
        jpShippingDate.add(jlShippingDate, BorderLayout.CENTER);

        jpTrackingNumber = new JPanel(new BorderLayout());
        jpTrackingNumber.setBackground(Color.WHITE);
        add(jpTrackingNumber);
        jlTrackingNumber = new JLabel("송장번호", SwingConstants.CENTER);
        jlTrackingNumber.setFont(font);
        jpTrackingNumber.add(jlTrackingNumber, BorderLayout.CENTER);

        jpShippingMethod = new JPanel(new BorderLayout());
        jpShippingMethod.setBackground(Color.WHITE);
        add(jpShippingMethod);
        jlShippingMethod = new JLabel("배송법", SwingConstants.CENTER);
        jlShippingMethod.setFont(font);
        jpShippingMethod.add(jlShippingMethod, BorderLayout.CENTER);

        jpCourier = new JPanel(new BorderLayout());
        jpCourier.setBackground(Color.WHITE);
        add(jpCourier);
        jlCourier = new JLabel("택배사", SwingConstants.CENTER);
        jlCourier.setFont(font);
        jpCourier.add(jlCourier, BorderLayout.CENTER);

        jpAddress = new JPanel(new BorderLayout());
        jpAddress.setBackground(Color.WHITE);
        add(jpAddress);
        jlAddress = new JLabel("주소", SwingConstants.CENTER);
        jlAddress.setFont(font);
        jpAddress.add(jlAddress, BorderLayout.CENTER);

        jpShippingMessage = new JPanel(new BorderLayout());
        jpShippingMessage.setBackground(Color.WHITE);
        add(jpShippingMessage);
        jlShippingMessage = new JLabel("배송메시지", SwingConstants.CENTER);
        jlShippingMessage.setFont(font);
        jpShippingMessage.add(jlShippingMessage, BorderLayout.CENTER);
    }

    public void setText(String image, String itemName, String itemQuantity, String itemPrice, String mallName, String orderNum,
                        String orderStatus, String option, String itemCode, String customerID, String customerName, String recipientName,
                        String phoneNum, String telNum, String email, String orderDate, String collectDate, String shippingDate,
                        String trackingNum, String shippingMethod, String courier, String address, String shippingMessage){

        jlImage.setText(image);
        jlItemName1.setText(itemName);
        jlItemQuantity.setText(itemQuantity);
        jlItemPrice1.setText(itemPrice);
        jlMallName.setText(mallName);
        jlOrderNum.setText(orderNum);
        jlOrderStatus.setText(orderStatus);
        jlItemName2.setText(itemName);
        jlOption.setText(option);
        jlItemCode.setText(itemCode);
        jlItemPrice2.setText(itemPrice);
        jlItemPrice3.setText(itemPrice);
        jlCustomerID.setText(customerID);
        jlCustomerName.setText(customerName);
        jlRecipientName.setText(recipientName);
        jlPhoneNum.setText(phoneNum);
        jlTelNum.setText(telNum);
        jlEmail.setText(email);
        jlOrderDate.setText(orderDate);
        jlCollectDate.setText(collectDate);
        jlShippingDate.setText(shippingDate);
        jlTrackingNumber.setText(trackingNum);
        jlShippingMethod.setText(shippingMethod);
        jlCourier.setText(courier);
        jlAddress.setText(address);
        jlShippingMessage.setText(shippingMessage);

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
//        g.drawImage(resizeImg, 0, 0, this); // see javadoc for more info on the parameters
        int x = (this.getWidth() - resizeImg.getWidth(null)) / 2;
        int y = (this.getHeight() - resizeImg.getHeight(null)) / 2;
        jpImage.setBounds(x + 144, 210, 80, 70);
        jpItemName1.setBounds(x + 232, 210, 250, 70);
        jpItemQuantity.setBounds(x + 490, 210, 80, 70);
        jpItemPrice1.setBounds(x + 577, 210, 80, 70);
        jpMallName.setBounds(x + 230, 368, 80, 35);
        jpOrderNum.setBounds(x + 403, 368, 80, 35);
        jpOrderStatus.setBounds(x + 577, 368, 80, 35);
        jpItemName2.setBounds(x + 230, 409, 427, 35);
        jpOption.setBounds(x + 230, 448, 427, 35);
        jpItemCode.setBounds(x + 230, 488, 80, 35);
        jpItemPrice2.setBounds(x + 404, 488, 80, 35);
        jpItemPrice3.setBounds(x + 577, 488, 80, 35);
        jpCustomerID.setBounds(x + 230, 528, 80, 35);
        jpCustomerName.setBounds(x + 404, 528, 80, 35);
        jpRecipientName.setBounds(x + 577, 528, 80, 35);
        jpPhoneNum.setBounds(x + 228, 568, 84, 35);
        jpTelNum.setBounds(x + 402, 568, 84, 35);
        jpEmail.setBounds(x + 575, 568, 84, 35);
        jpOrderDate.setBounds(x + 230, 688, 80, 35);
        jpCollectDate.setBounds(x + 404, 688, 80, 35);
        jpShippingDate.setBounds(x + 577, 688, 80, 35);
        jpTrackingNumber.setBounds(x + 230, 728, 80, 35);
        jpShippingMethod.setBounds(x + 404, 728, 80, 35);
        jpCourier.setBounds(x + 577, 728, 80, 35);
        jpAddress.setBounds(x + 230, 768, 427, 35);
        jpShippingMessage.setBounds(x + 230, 808, 427, 35);

        g.drawImage(resizeImg, x, y, null);
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