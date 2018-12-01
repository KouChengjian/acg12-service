import com.acg12.entity.dto.Acg12SubjectDto;
import com.acg12.utils.res.BgmResourceUtil;

/**
 * Created with IntelliJ IDEA.
 * User: KCJ
 * Date: 2018/11/30 11:48
 * Description:
 */
public class Test {

    public static void main(String[] args) {
        Acg12SubjectDto subjectDto = BgmResourceUtil.getSubjectDto(1005);
        System.out.println(subjectDto.toString());
    }
}
