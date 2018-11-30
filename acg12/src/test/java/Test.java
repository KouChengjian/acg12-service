import com.acg12.entity.dto.SubjectDto;
import com.acg12.utils.res.BgmResourceUtil;

/**
 * Created with IntelliJ IDEA.
 * User: KCJ
 * Date: 2018/11/30 11:48
 * Description:
 */
public class Test {

    public static void main(String[] args) {
        SubjectDto subjectDto = BgmResourceUtil.getSubjectDto(20100);
        System.out.println(subjectDto.toString());
    }
}
