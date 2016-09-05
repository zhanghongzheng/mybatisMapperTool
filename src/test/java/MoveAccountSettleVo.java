import com.hydra.tool.object.PojoConstantsUtil;

/**
 * Created by ZhengGong on 15/8/3.
 * Description 挪账、结算Vo
 */
public class MoveAccountSettleVo {
    private String merchantCode;
    private String amountTran;
    private String amountNoTran;
    private long merchantsettleId;
    private long merchantsettlemoveaccountId;
    private String trxId;
    private String businessId;

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public String getAmountTran() {
        return amountTran;
    }

    public void setAmountTran(String amountTran) {
        this.amountTran = amountTran;
    }

    public String getAmountNoTran() {
        return amountNoTran;
    }

    public void setAmountNoTran(String amountNoTran) {
        this.amountNoTran = amountNoTran;
    }

    public long getMerchantsettleId() {
        return merchantsettleId;
    }

    public void setMerchantsettleId(long merchantsettleId) {
        this.merchantsettleId = merchantsettleId;
    }

    public long getMerchantsettlemoveaccountId() {
        return merchantsettlemoveaccountId;
    }

    public void setMerchantsettlemoveaccountId(long merchantsettlemoveaccountId) {
        this.merchantsettlemoveaccountId = merchantsettlemoveaccountId;
    }

    public String getTrxId() {
        return trxId;
    }

    public void setTrxId(String trxId) {
        this.trxId = trxId;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    @Override
    public String toString() {
        return "MoveAccountSettleVo{" +
                "merchantCode='" + merchantCode + '\'' +
                ", amountTran='" + amountTran + '\'' +
                ", amountNoTran='" + amountNoTran + '\'' +
                ", merchantsettleId=" + merchantsettleId +
                ", merchantsettlemoveaccountId=" + merchantsettlemoveaccountId +
                ", trxId='" + trxId + '\'' +
                ", businessId='" + businessId + '\'' +
                '}';
    }

    public static void main(String[] args) {
        PojoConstantsUtil.console(MoveAccountSettleVo.class);
    }
}
