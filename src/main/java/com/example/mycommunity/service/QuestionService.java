package com.example.mycommunity.service;

import com.example.mycommunity.dto.PaginationDTO;
import com.example.mycommunity.dto.QuestionDTO;
import com.example.mycommunity.exception.CustomizeErrorCode;
import com.example.mycommunity.exception.CustomizeException;
import com.example.mycommunity.mapper.QuestionExtMapper;
import com.example.mycommunity.mapper.QuestionMapper;
import com.example.mycommunity.mapper.UserMapper;
import com.example.mycommunity.model.Question;
import com.example.mycommunity.model.QuestionExample;
import com.example.mycommunity.model.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * Create by czl on 2021/6/20 16:45
 */
@Service
public class QuestionService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionExtMapper questionExtMapper;

    public PaginationDTO list(Integer page, Integer size) {
        PaginationDTO paginationDTO=new PaginationDTO();
        QuestionExample questionExample=new QuestionExample();
        Integer totalPage;
        Integer totalCount=(int)questionMapper.countByExample(questionExample);
        if(totalCount%size==0){
            totalPage=totalCount/size;
        }else {
            totalPage=totalCount/size+1;
        }
        if(page<1){
            page=1;
        }
        if(page>totalPage){
            page=totalPage;
        }
        paginationDTO.setPagination(totalPage,page);

        Integer offset=(page-1)*size;
        List<Question> questions=questionMapper.selectByExampleWithRowbounds(new QuestionExample(),new RowBounds(offset,size));
        List<QuestionDTO> questionDTOList=new ArrayList<>();

        for (Question question:questions){
            User user=userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO=new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestioons(questionDTOList);
        return paginationDTO;
    }
    public  PaginationDTO listByUserId(Long userId,Integer page,Integer size){
        PaginationDTO paginationDTO=new PaginationDTO();
        Integer totalPage;
        QuestionExample questionExample=new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(userId);
        Integer totalCount=(int)questionMapper.countByExample(questionExample);
        if(totalCount%size==0){
            totalPage=totalCount/size;
        }else {
            totalPage=totalCount/size+1;
        }
        if(page<1){
            page=1;
        }
        if(page>totalPage){
            page=totalPage;
        }
        paginationDTO.setPagination(totalPage,page);
        Integer offset=(page-1)*size;
        QuestionExample example=new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(userId);
        List<Question> questions=questionMapper.selectByExampleWithRowbounds(example,new RowBounds(offset,size));
        List<QuestionDTO> questionDTOList=new ArrayList<>();

        for (Question question:questions){
            User user=userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO=new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestioons(questionDTOList);
        return paginationDTO;
    }

    public QuestionDTO getById(Long id) {
        Question question=questionMapper.selectByPrimaryKey(id);
        if(question==null){
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO=new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        User user=userMapper.selectByPrimaryKey(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if(question.getId()==null){
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(System.currentTimeMillis());
            question.setViewCount(0);
            question.setLikeCount(0);
            question.setCommentCount(0);
            questionMapper.insert(question);
        }else {
            Question updateQuestion=new Question();
            updateQuestion.setGmtModified(System.currentTimeMillis());
            updateQuestion.setTitle(question.getTitle());
            updateQuestion.setDescription(question.getDescription());
            updateQuestion.setTag(question.getTag());
            QuestionExample example=new QuestionExample();
            example.createCriteria().andIdEqualTo(question.getId());
            int updated=questionMapper.updateByExampleSelective(updateQuestion,example);
            if(updated!=1){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }

    public void incView(Long id) {
        Question question=new Question();
        question.setId(id);
        question.setViewCount(1);
        questionExtMapper.incView(question);
    }
}
