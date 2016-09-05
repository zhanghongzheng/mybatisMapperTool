import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import java.io.Serializable;
import java.util.Date;

@Table("account")
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String ID = "id";
    public static final String ACCOUNT_ID = "accountId";
    public static final String ACCOUNT_NAME = "accountName";
    public static final String USER_ID = "userId";
    public static final String FUND_VERSION = "fundVersion";
    public static final String SUBJECT_CODE = "subjectCode";
    public static final String STATUS = "status";
    public static final String BLOCK_MODE = "blockMode";
    public static final String CREATE_TIME = "createTime";
    public static final String UPDATE_TIME = "updateTime";
    public static final String INVALID_TIME = "invalidTime";
    public static final String ACCOUNT_TYPE = "accountType";
    public static final String ACCOUNT_SUB_TYPE = "accountSubType";
    public static final String CREDIT_AMOUNT = "creditAmount";
    public static final String CUR_ID = "curId";
    public static final String SUM_AMOUNT = "sumAmount";
    public static final String BLOCK_AMOUNT = "blockAmount";
    public static final String USABLE_AMOUNT = "usableAmount";
    public static final String WITHDRAW_AMOUNT = "withdrawAmount";
    public static final String LAST_TRAD_TIME = "lastTradTime";
    public static final String OLD_ACCOUNT_ID = "oldAccountId";
    public static final String OPERA_DIREC = "operaDirec";
    public static final String REMARK = "remark";
    public static final String EXT = "ext";

    @Id
    private Long id;

    @Column
    private String accountId;
    @Column
    private String accountName;
    @Column
    private String userId;
    @Column
    private Integer fundVersion;
    @Column
    private String subjectCode;
    @Column
    private Byte status;
    @Column
    private Byte blockMode;
    @Column
    private Date createTime;
    @Column
    private Date updateTime;
    @Column
    private Date invalidTime;
    @Column
    private String accountType;
    @Column
    private String accountSubType;
    @Column
    private Long creditAmount;
    @Column
    private String curId;
    @Column
    private Long sumAmount;
    @Column
    private Long blockAmount;
    @Column
    private Long usableAmount;
    @Column
    private Long withdrawAmount;
    @Column
    private Date lastTradTime;
    @Column
    private String oldAccountId;
    @Column
    private String operaDirec;
    @Column
    private String remark;
    @Column
    private String ext;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName == null ? null : accountName.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getFundVersion() {
        return fundVersion;
    }

    public void setFundVersion(Integer fundVersion) {
        this.fundVersion = fundVersion;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode == null ? null : subjectCode.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getBlockMode() {
        return blockMode;
    }

    public void setBlockMode(Byte blockMode) {
        this.blockMode = blockMode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getInvalidTime() {
        return invalidTime;
    }

    public void setInvalidTime(Date invalidTime) {
        this.invalidTime = invalidTime;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType == null ? null : accountType.trim();
    }

    public String getAccountSubType() {
        return accountSubType;
    }

    public void setAccountSubType(String accountSubType) {
        this.accountSubType = accountSubType == null ? null : accountSubType.trim();
    }

    public Long getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(Long creditAmount) {
        this.creditAmount = creditAmount;
    }

    public String getCurId() {
        return curId;
    }

    public void setCurId(String curId) {
        this.curId = curId == null ? null : curId.trim();
    }

    public Long getSumAmount() {
        return sumAmount;
    }

    public void setSumAmount(Long sumAmount) {
        this.sumAmount = sumAmount;
    }

    public Long getBlockAmount() {
        return blockAmount;
    }

    public void setBlockAmount(Long blockAmount) {
        this.blockAmount = blockAmount;
    }

    public Long getUsableAmount() {
        return usableAmount;
    }

    public void setUsableAmount(Long usableAmount) {
        this.usableAmount = usableAmount;
    }

    public Long getWithdrawAmount() {
        return withdrawAmount;
    }

    public void setWithdrawAmount(Long withdrawAmount) {
        this.withdrawAmount = withdrawAmount;
    }

    public Date getLastTradTime() {
        return lastTradTime;
    }

    public void setLastTradTime(Date lastTradTime) {
        this.lastTradTime = lastTradTime;
    }

    public String getOldAccountId() {
        return oldAccountId;
    }

    public void setOldAccountId(String oldAccountId) {
        this.oldAccountId = oldAccountId == null ? null : oldAccountId.trim();
    }

    public String getOperaDirec() {
        return operaDirec;
    }

    public void setOperaDirec(String operaDirec) {
        this.operaDirec = operaDirec == null ? null : operaDirec.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext == null ? null : ext.trim();
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountId='" + accountId + '\'' +
                ", accountName='" + accountName + '\'' +
                ", userId='" + userId + '\'' +
                ", fundVersion=" + fundVersion +
                ", subjectCode='" + subjectCode + '\'' +
                ", status=" + status +
                ", blockMode=" + blockMode +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", invalidTime=" + invalidTime +
                ", accountType='" + accountType + '\'' +
                ", accountSubType='" + accountSubType + '\'' +
                ", creditAmount=" + creditAmount +
                ", curId='" + curId + '\'' +
                ", sumAmount=" + sumAmount +
                ", blockAmount=" + blockAmount +
                ", usableAmount=" + usableAmount +
                ", withdrawAmount=" + withdrawAmount +
                ", lastTradTime=" + lastTradTime +
                ", oldAccountId='" + oldAccountId + '\'' +
                ", operaDirec='" + operaDirec + '\'' +
                ", remark='" + remark + '\'' +
                ", ext='" + ext + '\'' +
                '}';
    }
}