import com.acg12.entity.dto.Acg12CharacterDto;
import com.acg12.entity.dto.Acg12PersonDto;
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
        Acg12SubjectDto subjectDto = BgmResourceUtil.getSubjectDto(1010);
        System.out.println(subjectDto.toString());
//        Acg12PersonDto acg12PersonDto = BgmResourceUtil.getPersonDto(6000);
//        System.out.println(acg12PersonDto.toString());

//        Acg12CharacterDto characterDto = BgmResourceUtil.getCharacterDto(1);
//        System.out.println(characterDto.toString());
    }
}
