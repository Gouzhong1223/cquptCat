## 重游猫咪图鉴

打造更完美的重邮撸猫社区

```java
private static final String MAILSUBJECT = "重游猫咪图鉴";
    private static final Integer DEFAULTVIEWED = 0;
    private static final Integer DEFAULTAUDITED = 0;
    private final CatMapper catMapper;
    private final PraiseWechatUserMapper praiseWechatUserMapper;
    private final PicService picService;
    private final WechatUserMapper wechatUserMapper;
    private final MailService mailService;
    private final CatRefrrerMapper catRefrrerMapper;
    private final MailLogMapper mailLogMapper;
    private final CatCommentMapper catCommentMapper;
    private final CommentMapper commentMapper;
    private final AwesomeCatWechatUserMapper awesomeCatWechatUserMapper;
    private final CollectWechatUserMapper collectWechatUserMapper;
    private final AwesomeCommentWechatUserMapper awesomeCommentWechatUserMapper;
    private final CatPicMapper catPicMapper;
    private final PicMapper picMapper;
    private final CatRegionMapper catRegionMapper;
    private final RegionMapper regionMapper;

    @Value("${spring.mail.from}")
    private String from;

    public CatServiceImpl(CatMapper catMapper, PraiseWechatUserMapper praiseWechatUserMapper,
                          PicService picService, WechatUserMapper wechatUserMapper,
                          MailService mailService, CatRefrrerMapper catRefrrerMapper,
                          MailLogMapper mailLogMapper, CatCommentMapper catCommentMapper, CommentMapper commentMapper,
                          AwesomeCatWechatUserMapper awesomeCatWechatUserMapper, CollectWechatUserMapper collectWechatUserMapper,
                          AwesomeCommentWechatUserMapper awesomeCommentWechatUserMapper, CatPicMapper catPicMapper,
                          PicMapper picMapper, CatRegionMapper catRegionMapper, RegionMapper regionMapper) {
        this.catMapper = catMapper;
        this.praiseWechatUserMapper = praiseWechatUserMapper;
        this.picService = picService;
        this.wechatUserMapper = wechatUserMapper;
        this.mailService = mailService;
        this.catRefrrerMapper = catRefrrerMapper;
        this.mailLogMapper = mailLogMapper;
        this.catCommentMapper = catCommentMapper;
        this.commentMapper = commentMapper;
        this.awesomeCatWechatUserMapper = awesomeCatWechatUserMapper;
        this.collectWechatUserMapper = collectWechatUserMapper;
        this.awesomeCommentWechatUserMapper = awesomeCommentWechatUserMapper;
        this.catPicMapper = catPicMapper;
        this.picMapper = picMapper;
        this.catRegionMapper = catRegionMapper;
        this.regionMapper = regionMapper;
    }
```



