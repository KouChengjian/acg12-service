import com.acg12.entity.dto.Acg12PersonDto;
import com.acg12.entity.dto.Acg12SubjectDto;
import com.acg12.utils.res.BgmResourceUtil;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KCJ
 * Date: 2018/11/30 11:48
 * Description:
 */
public class Test {

    public static void main(String[] args) {
        Acg12SubjectDto subjectDto = BgmResourceUtil.getSubjectDto(1031);
        System.out.println(subjectDto.toString());
//        Acg12PersonDto acg12PersonDto = BgmResourceUtil.getPersonDto(6054);
//        System.out.println(acg12PersonDto.toString());

//        Acg12CharacterDto characterDto = BgmResourceUtil.getCharacterDto(1);
//        System.out.println(characterDto.toString());

//        List<Integer> list2 = BgmResourceUtil.characterType2();
//        System.out.println(list2.toString());

//        List<Integer> list3 = BgmResourceUtil.characterType3();
//        System.out.println(list3.toString());

//        List<Integer> list4 = BgmResourceUtil.characterType4();
//        System.out.println(list4.toString());
    }
}
