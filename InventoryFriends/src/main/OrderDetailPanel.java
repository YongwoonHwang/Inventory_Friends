package main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;

public class OrderDetailPanel extends JPanel{
    BufferedImage img;
    BufferedImage resizeImg;
    BufferedImage urlimg;
    BufferedImage resizeUrlImg;
    JPanel jpImage, jpItemName1, jpItemQuantity, jpTotalPaymentAmount, jpMallName, jpOrderNum, jpOrderStatus,
            jpItemName2, jpOption, jpItemCode, jpTotalProductAmount, jpUnitPrice, jpCustomerID, jpCustomerName,
            jpRecipientName, jpPhoneNum, jpTelNum, jpEmail, jpOrderDate, jpCollectDate, jpShippingDate,
            jpTrackingNumber, jpIndividualCustomUniqueCode, jpCourier, jpAddress, jpShippingMessage;
    JLabel  jlItemName1, jlItemQuantity, jlTotalPaymentAmount, jlMallName, jlOrderNum, jlOrderStatus,
            jlItemName2, jlOption, jlItemCode, jlTotalProductAmount, jlUnitPrice, jlCustomerID, jlCustomerName,
            jlRecipientName, jlPhoneNum, jlTelNum, jlEmail, jlOrderDate, jlCollectDate, jlShippingDate,
            jlTrackingNumber, jlIndividualCustomUniqueCode, jlCourier, jlAddress, jlShippingMessage;


    public OrderDetailPanel() {
        Font font1 = new Font("돋움", Font.PLAIN, 11);
        Font font2 = new Font("돋움", Font.PLAIN, 12);
        setLayout(null);
        setPreferredSize(new Dimension(800, 1000));
        try {
            img = ImageIO.read(getClass().getClassLoader().getResourceAsStream("img/img_Order.png"));
            resizeImg =  resize(img, 800, 1000);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        jpImage = new JPanel(new BorderLayout()){
            public void paint(Graphics g){
                g.drawImage(resizeUrlImg,0,0,null);
            }
        };
        jpImage.setBackground(Color.WHITE);
//        jpImage.setBorder(BorderFactory.createEtchedBorder());
        add(jpImage);
//        jlImage = new JLabel("이미지", SwingConstants.CENTER);
////        jlImage.setBorder(new LineBorder(Color.red));
//        jlImage.setFont(font2);
//        jpImage.add(jlImage, BorderLayout.CENTER);

        jpItemName1 = new JPanel(new BorderLayout());
        jpItemName1.setBackground(Color.WHITE);
        add(jpItemName1);
        jlItemName1 = new JLabel("제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목", SwingConstants.CENTER);
        jlItemName1.setFont(font2);
        jpItemName1.add(jlItemName1, BorderLayout.CENTER);

        jpItemQuantity = new JPanel(new BorderLayout());
        jpItemQuantity.setBackground(Color.WHITE);
        add(jpItemQuantity);
        jlItemQuantity = new JLabel("수량", SwingConstants.CENTER);
        jlItemQuantity.setFont(font2);
        jpItemQuantity.add(jlItemQuantity, BorderLayout.CENTER);

        jpTotalPaymentAmount = new JPanel(new BorderLayout());
        jpTotalPaymentAmount.setBackground(Color.WHITE);
        add(jpTotalPaymentAmount);
        jlTotalPaymentAmount = new JLabel("가격", SwingConstants.CENTER);
        jlTotalPaymentAmount.setFont(font2);
        jpTotalPaymentAmount.add(jlTotalPaymentAmount, BorderLayout.CENTER);

        jpMallName = new JPanel(new BorderLayout());
        jpMallName.setBackground(Color.WHITE);
        add(jpMallName);
        jlMallName = new JLabel("쇼핑몰", SwingConstants.CENTER);
        jlMallName.setFont(font2);
        jpMallName.add(jlMallName, BorderLayout.CENTER);

        jpOrderNum = new JPanel(new BorderLayout());
        jpOrderNum.setBackground(Color.WHITE);
        add(jpOrderNum);
        jlOrderNum = new JLabel("주문번호", SwingConstants.CENTER);
        jlOrderNum.setFont(font1);
        jpOrderNum.add(jlOrderNum, BorderLayout.CENTER);

        jpOrderStatus = new JPanel(new BorderLayout());
        jpOrderStatus.setBackground(Color.WHITE);
        add(jpOrderStatus);
        jlOrderStatus = new JLabel("주문 상태", SwingConstants.CENTER);
        jlOrderStatus.setFont(font2);
        jpOrderStatus.add(jlOrderStatus, BorderLayout.CENTER);

        jpItemName2 = new JPanel(new BorderLayout());
        jpItemName2.setBackground(Color.WHITE);
        add(jpItemName2);
        jlItemName2 = new JLabel("제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목", SwingConstants.CENTER);
        jlItemName2.setFont(font2);
        jpItemName2.add(jlItemName2, BorderLayout.CENTER);

        jpOption = new JPanel(new BorderLayout());
        jpOption.setBackground(Color.WHITE);
        add(jpOption);
        jlOption = new JLabel("옵션명", SwingConstants.CENTER);
        jlOption.setFont(font2);
        jpOption.add(jlOption, BorderLayout.CENTER);

        jpItemCode = new JPanel(new BorderLayout());
        jpItemCode.setBackground(Color.WHITE);
        add(jpItemCode);
        jlItemCode = new JLabel("상품 코드", SwingConstants.CENTER);
        jlItemCode.setFont(font2);
        jpItemCode.add(jlItemCode, BorderLayout.CENTER);

        jpTotalProductAmount = new JPanel(new BorderLayout());
        jpTotalProductAmount.setBackground(Color.WHITE);
        add(jpTotalProductAmount);
        jlTotalProductAmount = new JLabel("상품 금액", SwingConstants.CENTER);
        jlTotalProductAmount.setFont(font2);
        jpTotalProductAmount.add(jlTotalProductAmount, BorderLayout.CENTER);

        jpUnitPrice = new JPanel(new BorderLayout());
        jpUnitPrice.setBackground(Color.WHITE);
        add(jpUnitPrice);
        jlUnitPrice = new JLabel("판매 단가", SwingConstants.CENTER);
        jlUnitPrice.setFont(font2);
        jpUnitPrice.add(jlUnitPrice, BorderLayout.CENTER);

        jpCustomerID = new JPanel(new BorderLayout());
        jpCustomerID.setBackground(Color.WHITE);
        add(jpCustomerID);
        jlCustomerID = new JLabel("구매자 ID", SwingConstants.CENTER);
        jlCustomerID.setFont(font2);
        jpCustomerID.add(jlCustomerID, BorderLayout.CENTER);

        jpCustomerName = new JPanel(new BorderLayout());
        jpCustomerName.setBackground(Color.WHITE);
        add(jpCustomerName);
        jlCustomerName = new JLabel("구매자이름", SwingConstants.CENTER);
        jlCustomerName.setFont(font2);
        jpCustomerName.add(jlCustomerName, BorderLayout.CENTER);

        jpRecipientName = new JPanel(new BorderLayout());
        jpRecipientName.setBackground(Color.WHITE);
        add(jpRecipientName);
        jlRecipientName = new JLabel("수취인이름", SwingConstants.CENTER);
        jlRecipientName.setFont(font2);
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
        jlOrderDate.setFont(font2);
        jpOrderDate.add(jlOrderDate, BorderLayout.CENTER);

        jpCollectDate = new JPanel(new BorderLayout());
        jpCollectDate.setBackground(Color.WHITE);
        add(jpCollectDate);
        jlCollectDate = new JLabel("22023", SwingConstants.CENTER);
        jlCollectDate.setFont(font2);
        jpCollectDate.add(jlCollectDate, BorderLayout.CENTER);

        jpShippingDate = new JPanel(new BorderLayout());
        jpShippingDate.setBackground(Color.WHITE);
        add(jpShippingDate);
        jlShippingDate = new JLabel("220208", SwingConstants.CENTER);
        jlShippingDate.setFont(font2);
        jpShippingDate.add(jlShippingDate, BorderLayout.CENTER);

        jpTrackingNumber = new JPanel(new BorderLayout());
        jpTrackingNumber.setBackground(Color.WHITE);
        add(jpTrackingNumber);
        jlTrackingNumber = new JLabel("송장번호", SwingConstants.CENTER);
        jlTrackingNumber.setFont(font2);
        jpTrackingNumber.add(jlTrackingNumber, BorderLayout.CENTER);

        jpIndividualCustomUniqueCode = new JPanel(new BorderLayout());
        jpIndividualCustomUniqueCode.setBackground(Color.WHITE);
        add(jpIndividualCustomUniqueCode);
        jlIndividualCustomUniqueCode = new JLabel("배송법", SwingConstants.CENTER);
        jlIndividualCustomUniqueCode.setFont(font2);
        jpIndividualCustomUniqueCode.add(jlIndividualCustomUniqueCode, BorderLayout.CENTER);

        jpCourier = new JPanel(new BorderLayout());
        jpCourier.setBackground(Color.WHITE);
        add(jpCourier);
        jlCourier = new JLabel("택배사", SwingConstants.CENTER);
        jlCourier.setFont(font2);
        jpCourier.add(jlCourier, BorderLayout.CENTER);

        jpAddress = new JPanel(new BorderLayout());
        jpAddress.setBackground(Color.WHITE);
        add(jpAddress);
        jlAddress = new JLabel("주소", SwingConstants.CENTER);
        jlAddress.setFont(font2);
        jpAddress.add(jlAddress, BorderLayout.CENTER);

        jpShippingMessage = new JPanel(new BorderLayout());
        jpShippingMessage.setBackground(Color.WHITE);
        add(jpShippingMessage);
        jlShippingMessage = new JLabel("배송메시지", SwingConstants.CENTER);
        jlShippingMessage.setFont(font2);
        jpShippingMessage.add(jlShippingMessage, BorderLayout.CENTER);
    }

    public void setImage(String img){
        try {
            URL url = new URL(img);
            urlimg = ImageIO.read(url);
            resizeUrlImg = resize(urlimg, 80, 70);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void setText(String image, String productName, String quantity, String totalPaymentAmount,
                        String totalProductAmount, String unitPrice, String market, String orderId,
                        String productOrderStatus, String productOption, String sellerProductCode,
                        String ordererId, String ordererName, String name,
                        String ordererTel, String tel1, String email, String orderDate, 
                        String pickupDate, String sendDate, String trackingNumber,
                        String individualCustomUniqueCode, String deliveryCompany, String baseAddress,
                        String shippingMemo){

        setImage(image);
        jlItemName1.setText(productName);
        jlItemQuantity.setText(quantity);
        jlTotalPaymentAmount.setText(totalPaymentAmount);
        jlMallName.setText(market);
        jlOrderNum.setText(orderId);
        jlOrderStatus.setText(productOrderStatus);
        jlItemName2.setText(productName);
        jlOption.setText(productOption);
        jlItemCode.setText(sellerProductCode);
        jlTotalProductAmount.setText(totalProductAmount);
        jlUnitPrice.setText(unitPrice);
        jlCustomerID.setText(ordererId);
        jlCustomerName.setText(ordererName);
        jlRecipientName.setText(name);
        jlPhoneNum.setText(ordererTel);
        jlTelNum.setText(tel1);
        jlEmail.setText(email);
        jlOrderDate.setText(orderDate);
        jlCollectDate.setText(pickupDate);
        jlShippingDate.setText(sendDate);
        jlTrackingNumber.setText(trackingNumber);
        jlIndividualCustomUniqueCode.setText(individualCustomUniqueCode);
        jlCourier.setText(deliveryCompany);
        jlAddress.setText(baseAddress);
        jlShippingMessage.setText(shippingMemo);

        revalidate();
        repaint();
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
        jpImage.setBounds(x + 144, y + 210, 80, 70);
        jpItemName1.setBounds(x + 232, y + 210, 250, 70);
        jpItemQuantity.setBounds(x + 490, y + 210, 80, 70);
        jpTotalPaymentAmount.setBounds(x + 577, y + 210, 80, 70);
        jpMallName.setBounds(x + 230, y + 368, 80, 35);
        jpOrderNum.setBounds(x + 403, y + 368, 80, 35);
        jpOrderStatus.setBounds(x + 577, y + 368, 80, 35);
        jpItemName2.setBounds(x + 230, y + 409, 427, 35);
        jpOption.setBounds(x + 230, y + 448, 427, 35);
        jpItemCode.setBounds(x + 230, y + 488, 80, 35);
        jpTotalProductAmount.setBounds(x + 404, y + 488, 80, 35);
        jpUnitPrice.setBounds(x + 577, y + 488, 80, 35);
        jpCustomerID.setBounds(x + 230, y + 528, 80, 35);
        jpCustomerName.setBounds(x + 404, y + 528, 80, 35);
        jpRecipientName.setBounds(x + 577, y + 528, 80, 35);
        jpPhoneNum.setBounds(x + 228, y + 568, 84, 35);
        jpTelNum.setBounds(x + 402, y + 568, 84, 35);
        jpEmail.setBounds(x + 575, y + 568, 84, 35);
        jpOrderDate.setBounds(x + 230, y + 688, 80, 35);
        jpCollectDate.setBounds(x + 404, y + 688, 80, 35);
        jpShippingDate.setBounds(x + 577, y + 688, 80, 35);
        jpTrackingNumber.setBounds(x + 230, y + 728, 80, 35);
        jpIndividualCustomUniqueCode.setBounds(x + 404, y + 728, 80, 35);
        jpCourier.setBounds(x + 577, y + 728, 80, 35);
        jpAddress.setBounds(x + 230, y + 768, 427, 35);
        jpShippingMessage.setBounds(x + 230, y + 808, 427, 35);

        g.drawImage(resizeImg, x, y, null);
    }

}