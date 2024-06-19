USE [MasterIOTDIGITALPAYMENT]
GO
/****** Object:  Table [dbo].[Initiate_Transaction_Response]    Script Date: 6/19/2024 1:07:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Initiate_Transaction_Response](
	[customer_id] [varchar](256) NULL,
	[account_id] [varchar](256) NULL,
	[account_currency] [varchar](256) NULL,
	[transaction_date_time] [datetime2](7) NULL,
	[transaction_id] [varchar](255) NOT NULL,
	[token] [nchar](255) NULL,
	[refresh_token] [nchar](255) NULL,
	[automatic] [nchar](10) NULL,
	[Percentage] [nchar](10) NULL,
	[email] [varchar](255) NULL,
	[mail_sent] [bit] NULL,
	[active] [bit] NULL,
 CONSTRAINT [PK_Initiate_Transaction_Response] PRIMARY KEY CLUSTERED 
(
	[transaction_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[initiated_transactions]    Script Date: 6/19/2024 1:07:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[initiated_transactions](
	[customer_id] [varchar](256) NULL,
	[account_id] [varchar](256) NULL,
	[account_currency] [varchar](256) NULL,
	[transaction_date_time] [datetime2](7) NULL,
	[transaction_id] [varchar](255) NOT NULL,
	[automatic] [nchar](10) NULL,
	[Percentage] [nchar](10) NULL,
	[email] [varchar](255) NULL,
 CONSTRAINT [PK_initiated_transactions] PRIMARY KEY CLUSTERED 
(
	[transaction_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[refreshtoken]    Script Date: 6/19/2024 1:07:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[refreshtoken](
	[id] [varchar](50) NOT NULL,
	[token] [varchar](255) NOT NULL,
	[expirydate] [datetime2](7) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[token] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
